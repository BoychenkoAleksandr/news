package com.boic.testTask.service;

import com.boic.testTask.comments.*;
import com.boic.testTask.news.News;
import com.boic.testTask.news.NewsJpa;
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
import org.springframework.data.jpa.domain.Specification;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentsServiceTest {

    @Mock
    private CommentsRepository commentsRepository;

    @Mock
    private CommentsJpaMapper commentsJpaMapper;

    @InjectMocks
    private CommentsService commentsService;

    // Тестовые данные
    private final Long testId = 1L;
    private final Long newsId = 10L;
    private final Long authorId = 100L;
    private final Long otherUserId = 200L;
    private Comments testComment;
    private CommentsJpa testCommentJpa;
    private final Pageable pageable = PageRequest.of(0, 10);
    private News news;

    @BeforeEach
    void setUp() {
        news = new News();
        news.setId(10L);
        testComment = new Comments();
        testComment.setId(1L);
        testComment.setText("Test Content");
        testComment.setInsertedById(authorId);
        testComment.setNews(news);

        NewsJpa newsJpa = new NewsJpa();
        news.setId(10L);
        testCommentJpa = new CommentsJpa();
        testCommentJpa.setId(1L);
        testCommentJpa.setText("Test Content");
        testCommentJpa.setInsertedById(authorId);
        testCommentJpa.setNews(newsJpa);
    }

    @Test
    void getAllCommentsByNewsId_ShouldReturnPageOfComments() {
        // Подготовка
        Page<CommentsJpa> commentJpaPage = new PageImpl<>(Collections.singletonList(testCommentJpa));
        Page<Comments> expectedPage = new PageImpl<>(Collections.singletonList(testComment));

        when(commentsRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(commentJpaPage);
        when(commentsJpaMapper.fromJpaEntity(commentJpaPage)).thenReturn(expectedPage);

        // Действие
        Page<Comments> result = commentsService.getAllCommentsByNewsId(newsId, pageable);

        // Проверка
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testComment, result.getContent().get(0));

        // Проверка вызовов
        verify(commentsRepository, times(1)).findAll(any(Specification.class), eq(pageable));
        verify(commentsJpaMapper, times(1)).fromJpaEntity(commentJpaPage);
    }

    @Test
    void updateComments_ShouldUpdateComment_WhenUserIsAuthor() {
        // Подготовка
        Comments updatedComment = new Comments();
        updatedComment.setId(1L);
        updatedComment.setText("Updated comment");
        updatedComment.setInsertedById(authorId);
        updatedComment.setNews(news);
        Optional<CommentsJpa> optionalCommentJpa = Optional.of(testCommentJpa);

        when(commentsRepository.findById(testId)).thenReturn(optionalCommentJpa);
        when(commentsJpaMapper.fromJpaEntity(optionalCommentJpa)).thenReturn(Optional.ofNullable(testComment));
        when(commentsJpaMapper.toJpaEntity(any(Comments.class))).thenReturn(testCommentJpa);
        when(commentsRepository.save(testCommentJpa)).thenReturn(testCommentJpa);
        when(commentsJpaMapper.fromJpaEntity(testCommentJpa)).thenReturn(updatedComment);

        // Действие
        Comments result = commentsService.updateComments(testId, updatedComment.getText(), authorId, "ROLE_JOURNALIST");

        // Проверка
        assertNotNull(result);
        assertEquals("Updated comment", result.getText());

        // Проверка вызовов
        verify(commentsRepository, times(1)).findById(testId);
        verify(commentsJpaMapper, times(1)).toJpaEntity(any(Comments.class));
        verify(commentsRepository, times(1)).save(testCommentJpa);
    }

    @Test
    void updateComments_ShouldUpdateComment_WhenUserIsAdmin() {
        // Подготовка
        Comments updatedComment = new Comments();
        updatedComment.setId(1L);
        updatedComment.setText("Updated comment");
        updatedComment.setInsertedById(authorId);
        updatedComment.setNews(news);
        Optional<CommentsJpa> optionalCommentJpa = Optional.of(testCommentJpa);

        when(commentsRepository.findById(testId)).thenReturn(optionalCommentJpa);
        when(commentsJpaMapper.fromJpaEntity(optionalCommentJpa)).thenReturn(Optional.ofNullable(testComment));
        when(commentsJpaMapper.toJpaEntity(any(Comments.class))).thenReturn(testCommentJpa);
        when(commentsRepository.save(testCommentJpa)).thenReturn(testCommentJpa);
        when(commentsJpaMapper.fromJpaEntity(testCommentJpa)).thenReturn(updatedComment);

        // Действие
        Comments result = commentsService.updateComments(testId, updatedComment.getText(), otherUserId, "ROLE_ADMIN");

        // Проверка
        assertNotNull(result);
        assertEquals("Updated comment", result.getText());

        // Проверка вызовов
        verify(commentsRepository, times(1)).findById(testId);
        verify(commentsJpaMapper, times(1)).toJpaEntity(any(Comments.class));
        verify(commentsRepository, times(1)).save(testCommentJpa);
    }

    @Test
    void delete_ShouldDeleteComment_WhenUserIsAuthor() {
        // Подготовка
        when(commentsRepository.findById(testId)).thenReturn(Optional.of(testCommentJpa));

        // Действие
        commentsService.delete(testId, authorId, "ROLE_JOURNALIST");

        // Проверка вызова
        verify(commentsRepository, times(1)).delete(testCommentJpa);
    }

    @Test
    void delete_ShouldDeleteComment_WhenUserIsAdmin() {
        // Подготовка
        when(commentsRepository.findById(testId)).thenReturn(Optional.of(testCommentJpa));

        // Действие
        commentsService.delete(testId, otherUserId, "ROLE_ADMIN");

        // Проверка вызова
        verify(commentsRepository, times(1)).delete(testCommentJpa);
    }

    @Test
    void getById_ShouldReturnComment_WhenExists() {
        // Подготовка
        Optional<CommentsJpa> optionalCommentJpa = Optional.of(testCommentJpa);

        when(commentsRepository.findById(testId)).thenReturn(optionalCommentJpa);
        when(commentsJpaMapper.fromJpaEntity(optionalCommentJpa)).thenReturn(Optional.ofNullable(testComment));

        // Действие
        Comments result = commentsService.getById(testId);

        // Проверка
        assertNotNull(result);
        assertEquals(testComment, result);

        // Проверка вызова
        verify(commentsRepository, times(1)).findById(testId);
    }

    @Test
    void persist_ShouldSaveComment() {
        // Подготовка
        when(commentsJpaMapper.toJpaEntity(testComment)).thenReturn(testCommentJpa);
        when(commentsRepository.save(testCommentJpa)).thenReturn(testCommentJpa);
        when(commentsJpaMapper.fromJpaEntity(testCommentJpa)).thenReturn(testComment);

        // Действие
        Comments result = commentsService.persist(testComment);

        // Проверка
        assertNotNull(result);

        // Проверка вызова
        verify(commentsRepository, times(1)).save(testCommentJpa);
    }
}
