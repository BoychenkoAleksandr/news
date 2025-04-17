package com.boic.testTask.controller;

import com.boic.testTask.configuration.CustomUserDetails;
import com.boic.testTask.comments.*;
import com.boic.testTask.news.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CommentsControllerTest {
    @Mock
    private CommentsService commentsService;

    @InjectMocks
    private CommentsController commentsController;

    @Mock
    private CommentsMapper commentsMapper;

    // Тестовые данные
    private final Long testId = 1L;
    private final Long testNewsId = 10L;
    private CommentsDtoIn commentDtoIn;
    private Comments comment;
    private CommentsDtoOut commentDtoOut;
    private CustomUserDetails userDetails;
    @BeforeEach
    void setUp() {
        commentDtoIn = new CommentsDtoIn();
        commentDtoIn.setText("Test Content");

        comment = new Comments();
        comment.setId(1L);
        comment.setText("Test Content");
        News news = new News();
        news.setId(10L);
        comment.setNews(news);

        commentDtoOut = new CommentsDtoOut();
        commentDtoOut.setId(1L);
        commentDtoOut.setText("Test Content");
        commentDtoOut.setNewsId(10L);

        userDetails = new CustomUserDetails(
                1L, "testUser", "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void getCommentsById_ShouldReturnComment_WhenCommentExists() {
        // Подготовка
        when(commentsService.getById(testId)).thenReturn(comment);
        when(commentsMapper.toOut(comment)).thenReturn(commentDtoOut);

        // Действие
        ResponseEntity<CommentsDtoOut> response = commentsController.getCommentsById(testId);

        // Проверка
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDtoOut, response.getBody());

        // Проверка вызовов
        verify(commentsService, times(1)).getById(testId);
        verify(commentsMapper, times(1)).toOut(comment);
    }

    @Test
    void getAllCommentsByNewsId_ShouldReturnPageOfComments() {
        // Подготовка
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        List<Comments> commentsList = Collections.singletonList(comment);
        Page<Comments> commentsPage = new PageImpl<>(commentsList, pageable, 1);

        when(commentsService.getAllCommentsByNewsId(testNewsId, pageable)).thenReturn(commentsPage);
        when(commentsMapper.toOut(commentsPage)).thenReturn(new PageImpl<>(Collections.singletonList(commentDtoOut)));

        // Действие
        ResponseEntity<Page<CommentsDtoOut>> response =
                commentsController.getAllCommentsByNewsId(testNewsId, page, size);

        // Проверка
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
        assertEquals(testNewsId, response.getBody().getContent().get(0).getNewsId()); // предполагая, что DTO содержит newsId

        // Проверка вызовов
        verify(commentsService, times(1)).getAllCommentsByNewsId(testNewsId, pageable);
        verify(commentsMapper, times(1)).toOut(commentsPage);
    }

    @Test
    void createComments_ShouldReturnCreatedComment() {
        // Подготовка
        when(commentsMapper.fromIn(commentDtoIn)).thenReturn(comment);
        when(commentsService.persist(comment)).thenReturn(comment);
        when(commentsMapper.toOut(comment)).thenReturn(commentDtoOut);

        // Действие
        ResponseEntity<CommentsDtoOut> response =
                commentsController.createComments(commentDtoIn, userDetails);

        // Проверка
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(commentDtoOut, response.getBody());

        // Проверка вызовов
        verify(commentsMapper, times(1)).fromIn(commentDtoIn);
        verify(commentsService, times(1)).persist(comment);
        verify(commentsMapper, times(1)).toOut(comment);
    }

    @Test
    void updateComments_ShouldReturnUpdatedComment_WhenUserHasPermission() {
        // Подготовка
        when(commentsMapper.fromIn(commentDtoIn)).thenReturn(comment);
        when(commentsService.updateComments(
                eq(testId),
                eq(comment.getText()),
                eq(userDetails.getId()),
                any(String.class))
        ).thenReturn(comment);
        when(commentsMapper.toOut(comment)).thenReturn(commentDtoOut);

        // Действие
        ResponseEntity<CommentsDtoOut> response =
                commentsController.updateComments(testId, commentDtoIn, userDetails);

        // Проверка
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDtoOut, response.getBody());

        // Проверка вызовов
        verify(commentsMapper, times(1)).fromIn(commentDtoIn);
        verify(commentsService, times(1)).updateComments(
                eq(testId),
                eq(comment.getText()),
                eq(userDetails.getId()),
                any(String.class));
        verify(commentsMapper, times(1)).toOut(comment);
    }

    @Test
    void deleteComments_ShouldInvokeService_WhenUserHasPermission() {
        // Действие
        commentsController.deleteComments(testId, userDetails);

        // Проверка
        verify(commentsService, times(1)).delete(
                eq(testId),
                eq(userDetails.getId()),
                any(String.class));
    }
}
