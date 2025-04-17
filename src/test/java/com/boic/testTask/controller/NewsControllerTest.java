package com.boic.testTask.controller;

import com.boic.testTask.configuration.CustomUserDetails;
import com.boic.testTask.news.*;
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
class NewsControllerTest {
    @Mock
    private NewsService newsService;

    @InjectMocks
    private NewsController newsController;

    @Mock
    private NewsMapper newsMapper;

    // Тестовые данные
    private final Long testId = 1L;
    private NewsDtoIn newsDtoIn;
    private News news;
    private NewsDtoOut newsDtoOut;
    private final CustomUserDetails userDetails = new CustomUserDetails(
            1L, "testUser", "password",
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));;
    @BeforeEach
    void setUp() {
        newsDtoIn = new NewsDtoIn();
        newsDtoIn.setTitle("Test Title");
        newsDtoIn.setText("Test Content");

        news = new News();
        news.setId(1L);
        news.setTitle("Test Title");
        news.setText("Test Content");

        newsDtoOut = new NewsDtoOut();
        newsDtoOut.setId(1L);
        newsDtoOut.setTitle("Test Title");
        newsDtoOut.setText("Test Content");
    }
    @Test
    void getNewsById_ShouldReturnNews_WhenNewsExists() {
        // Подготовка
        when(newsService.getById(testId)).thenReturn(news);
        when(newsMapper.toOut(news)).thenReturn(newsDtoOut);

        // Действие
        ResponseEntity<NewsDtoOut> response  = newsController.getNewsById(testId);

        // Проверка
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newsDtoOut, response.getBody());

        // Проверка вызовов
        verify(newsService, times(1)).getById(testId);
        verify(newsMapper, times(1)).toOut(news);
    }

    @Test
    void getAllNews_ShouldReturnPageOfNews() {
        // Подготовка
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        List<News> newsList = Collections.singletonList(news);
        Page<News> newsPage = new PageImpl<>(newsList, pageable, 1);

        when(newsService.getAllNews(pageable)).thenReturn(newsPage);
        when(newsMapper.toOut(newsPage)).thenReturn(new PageImpl<>(Collections.singletonList(newsDtoOut)));

        // Действие
        ResponseEntity<Page<NewsDtoOut>> response = newsController.getAllNews(page, size);

        // Проверка
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());

        // Проверка вызовов
        verify(newsService).getAllNews(pageable);
        verify(newsMapper).toOut(newsPage);
    }

    @Test
    void findNewsByTitleTextLike_ShouldReturnFilteredNews() {
        // Подготовка
        String title = "test";
        String text = "content";
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        List<News> newsList = Collections.singletonList(news);
        Page<News> newsPage = new PageImpl<>(newsList, pageable, 1);

        when(newsService.findByTitleTextLike(title, text, pageable)).thenReturn(newsPage);
        when(newsMapper.toOut(newsPage)).thenReturn(new PageImpl<>(Collections.singletonList(newsDtoOut)));

        // Действие
        ResponseEntity<Page<NewsDtoOut>> response = newsController.findNewsByTitleTextLike(
                title, text, page, size);

        // Проверка
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());

        // Проверка вызовов
        verify(newsService).findByTitleTextLike(title, text, pageable);
        verify(newsMapper).toOut(newsPage);
    }

    @Test
    void createNews_ShouldReturnCreatedNews() {
        // Подготовка
        when(newsMapper.fromIn(newsDtoIn)).thenReturn(news);
        when(newsService.persist(news)).thenReturn(news);
        when(newsMapper.toOut(news)).thenReturn(newsDtoOut);

        // Действие
        ResponseEntity<NewsDtoOut> response = newsController.createNews(newsDtoIn, userDetails);

        // Проверка
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newsDtoOut, response.getBody());

        // Проверка вызовов
        verify(newsMapper).fromIn(newsDtoIn);
        verify(newsService).persist(news);
        verify(newsMapper).toOut(news);
    }

    @Test
    void updateNews_ShouldReturnUpdatedNews_WhenUserHasPermission() {
        // Подготовка
        when(newsMapper.fromIn(newsDtoIn)).thenReturn(news);
        when(newsService.updateNews(
                eq(testId),
                eq(news),
                eq(userDetails.getId()),
                any(String.class))
        ).thenReturn(news);
        when(newsMapper.toOut(news)).thenReturn(newsDtoOut);

        // Действие
        ResponseEntity<NewsDtoOut> response = newsController.updateNews(
                testId, newsDtoIn, userDetails);

        // Проверка
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newsDtoOut, response.getBody());

        // Проверка вызовов
        verify(newsMapper).fromIn(newsDtoIn);
        verify(newsService).updateNews(
                eq(testId),
                eq(news),
                eq(userDetails.getId()),
                any(String.class));
        verify(newsMapper).toOut(news);
    }

    @Test
    void deleteNews_ShouldInvokeService_WhenUserHasPermission() {
        // настройка моков не требуется, так как метод void

        // Действие
        newsController.deleteNews(testId, userDetails);

        // Проверка - проверяем, что метод сервиса был вызван с правильными параметрами
        verify(newsService).delete(
                eq(testId),
                eq(userDetails.getId()),
                any(String.class));
    }
}
