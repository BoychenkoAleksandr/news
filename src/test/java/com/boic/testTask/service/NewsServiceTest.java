package com.boic.testTask.service;

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
import org.springframework.data.jpa.domain.Specification;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private NewsJpaMapper newsJpaMapper;

    @InjectMocks
    private NewsService newsService;

    // Тестовые данные
    private final Long testId = 1L;
    private final Long authorId = 10L;
    private final Long otherUserId = 20L;
    private News testNews;
    private NewsJpa testNewsJpa;
    private final Pageable pageable = PageRequest.of(0, 10);

    @BeforeEach
    void setUp() {
        testNews = new News();
        testNews.setId(testId);
        testNews.setTitle("Test Title");
        testNews.setText("Test Text");
        testNews.setLastEditDate(LocalDateTime.now());
        testNews.setCreationDate(LocalDateTime.now());
        testNews.setUpdatedById(authorId);
        testNews.setInsertedById(authorId);

        testNewsJpa = new NewsJpa();
        testNewsJpa.setId(testId);
        testNewsJpa.setTitle("Test Title");
        testNewsJpa.setText("Test Text");
        testNewsJpa.setLastEditDate(LocalDateTime.now());
        testNewsJpa.setCreationDate(LocalDateTime.now());
        testNewsJpa.setUpdatedById(authorId);
        testNewsJpa.setInsertedById(authorId);
    }

    @Test
    void getAllNews_ShouldReturnPageOfNews() {
        // Подготовка
        Page<NewsJpa> newsJpaPage = new PageImpl<>(Collections.singletonList(testNewsJpa));
        Page<News> expectedPage = new PageImpl<>(Collections.singletonList(testNews));

        when(newsRepository.findAll(pageable)).thenReturn(newsJpaPage);
        when(newsJpaMapper.fromJpaEntity(newsJpaPage)).thenReturn(expectedPage);

        // Действие
        Page<News> result = newsService.getAllNews(pageable);

        // Проверка
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testNews, result.getContent().get(0));

        // Проверка вызовов
        verify(newsRepository, times(1)).findAll(pageable);
        verify(newsJpaMapper, times(1)).fromJpaEntity(newsJpaPage);
    }

    @Test
    void findByTitleTextLike_ShouldReturnFilteredNews() {
        // Подготовка
        String title = "Test";
        String text = "Text";
        Page<NewsJpa> newsJpaPage = new PageImpl<>(Collections.singletonList(testNewsJpa));
        Page<News> expectedPage = new PageImpl<>(Collections.singletonList(testNews));

        when(newsRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(newsJpaPage);
        when(newsJpaMapper.fromJpaEntity(newsJpaPage)).thenReturn(expectedPage);

        // Действие
        Page<News> result = newsService.findByTitleTextLike(title, text, pageable);

        // Проверка
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testNews, result.getContent().get(0));

        // Проверка вызовов
        verify(newsRepository, times(1)).findAll(any(Specification.class), eq(pageable));
        verify(newsJpaMapper, times(1)).fromJpaEntity(newsJpaPage);
    }

    @Test
    void updateNews_ShouldUpdateNews_WhenUserIsAuthor() {
        // Подготовка
        News updatedNews = new News();
        updatedNews.setId(testId);
        updatedNews.setTitle("Updated Title");
        updatedNews.setText("Updated Text");
        updatedNews.setLastEditDate(LocalDateTime.now());
        updatedNews.setCreationDate(LocalDateTime.now());
        updatedNews.setUpdatedById(authorId);
        updatedNews.setInsertedById(authorId);

        when(newsRepository.findById(testId)).thenReturn(Optional.of(testNewsJpa));
        when(newsJpaMapper.fromJpaEntity(Optional.of(testNewsJpa))).thenReturn(Optional.ofNullable(testNews));
        when(newsJpaMapper.toJpaEntity(any(News.class))).thenReturn(testNewsJpa);
        when(newsRepository.save(testNewsJpa)).thenReturn(testNewsJpa);
        when(newsJpaMapper.fromJpaEntity(testNewsJpa)).thenReturn(updatedNews);

        // Действие
        News result = newsService.updateNews(testId, updatedNews, authorId, "ROLE_JOURNALIST");

        // Проверка
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Text", result.getText());

        // Проверка вызовов
        verify(newsRepository, times(1)).findById(testId);
        verify(newsJpaMapper, times(1)).toJpaEntity(any(News.class));
        verify(newsRepository, times(1)).save(testNewsJpa);
    }

    @Test
    void updateNews_ShouldUpdateNews_WhenUserIsAdmin() {
        News updatedNews = new News();
        updatedNews.setId(testId);
        updatedNews.setTitle("Updated Title");
        updatedNews.setText("Updated Text");
        updatedNews.setLastEditDate(LocalDateTime.now());
        updatedNews.setCreationDate(LocalDateTime.now());
        updatedNews.setUpdatedById(authorId);
        updatedNews.setInsertedById(authorId);

        when(newsRepository.findById(testId)).thenReturn(Optional.of(testNewsJpa));
        when(newsJpaMapper.fromJpaEntity(Optional.of(testNewsJpa))).thenReturn(Optional.ofNullable(testNews));
        when(newsJpaMapper.toJpaEntity(any(News.class))).thenReturn(testNewsJpa);
        when(newsRepository.save(testNewsJpa)).thenReturn(testNewsJpa);
        when(newsJpaMapper.fromJpaEntity(testNewsJpa)).thenReturn(updatedNews);

        // Действие
        News result = newsService.updateNews(testId, updatedNews, otherUserId, "ROLE_ADMIN");

        // Проверка
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Text", result.getText());

        // Проверка вызовов
        verify(newsRepository, times(1)).findById(testId);
        verify(newsJpaMapper, times(1)).toJpaEntity(any(News.class));
        verify(newsRepository, times(1)).save(testNewsJpa);
    }

    @Test
    void delete_ShouldDeleteNews_WhenUserIsAuthor() {
        // Подготовка
        when(newsRepository.findById(testId)).thenReturn(Optional.of(testNewsJpa));

        // Действие
        newsService.delete(testId, authorId, "ROLE_JOURNALIST");

        // Проверка вызова
        verify(newsRepository, times(1)).delete(testNewsJpa);
    }

    @Test
    void delete_ShouldDeleteNews_WhenUserIsAdmin() {
        // Подготовка
        when(newsRepository.findById(testId)).thenReturn(Optional.of(testNewsJpa));

        // Действие
        newsService.delete(testId, otherUserId, "ROLE_ADMIN");

        // Проверка вызова
        verify(newsRepository, times(1)).delete(testNewsJpa);
    }

    @Test
    void getById_ShouldReturnNews_WhenExists() {
        // Подготовка
        Optional<NewsJpa> optionalCommentJpa = Optional.of(testNewsJpa);

        when(newsRepository.findById(testId)).thenReturn(optionalCommentJpa);
        when(newsJpaMapper.fromJpaEntity(optionalCommentJpa)).thenReturn(Optional.ofNullable(testNews));

        // Действие
        News result = newsService.getById(testId);

        // Проверка
        assertNotNull(result);
        assertEquals(testNews, result);

        // Проверка вызова
        verify(newsRepository, times(1)).findById(testId);
    }

    @Test
    void persist_ShouldSaveNews() {
        // Подготовка
        when(newsJpaMapper.toJpaEntity(testNews)).thenReturn(testNewsJpa);
        when(newsRepository.save(testNewsJpa)).thenReturn(testNewsJpa);
        when(newsJpaMapper.fromJpaEntity(testNewsJpa)).thenReturn(testNews);

        // Действие
        News result = newsService.persist(testNews);

        // Проверка
        assertNotNull(result);

        // Проверка вызова
        verify(newsRepository, times(1)).save(testNewsJpa);
    }
}
