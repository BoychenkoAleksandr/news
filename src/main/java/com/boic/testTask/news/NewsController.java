package com.boic.testTask.news;

import com.boic.testTask.configuration.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

/**
 * REST-контроллер для работы с новостями.
 * <p>
 * Предоставляет CRUD-операции для новостей с пагинацией и поиском.
 * Требует аутентификации для создания/изменения/удаления записей.
 * </p>
 *
 * @author Boichanka
 * @version 1.0
 */
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@Slf4j
public class NewsController {
    private final NewsService newsService;
    private final NewsMapper newsMapper;

    /**
     * Получает новость по ID.
     *
     * @param id ID новости (обязательно)
     * @return {@link NewsDtoOut} данные новости (id, заголовок, текст)
     * @apiNote Пример:
     * <pre>GET /api/news/123</pre>
     * @response code 200 Новость найдена
     * @response code 404 Новость не существует
     */
    @GetMapping("/{id}")
    public ResponseEntity<NewsDtoOut> getNewsById(@PathVariable Long id) {
        log.debug("Fetching news by id: {}", id);
        News findNews = newsService.getById(id);
        log.debug("Successfully fetched news with id: {}", id);
        return ResponseEntity.ok(newsMapper.toOut(findNews));
    }

    /**
     * Получает все новости с пагинацией.
     *
     * @param page номер страницы (по умолчанию 0)
     * @param size размер страницы (по умолчанию 10)
     * @return страница с {@link NewsDtoOut}
     * @apiNote Пример:
     * <pre>GET /api/news/getAllNews?page=0&size=5</pre>
     * @response code 200 Успешный запрос
     */
    @GetMapping("/getAllNews")
    public ResponseEntity<Page<NewsDtoOut>> getAllNews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(newsMapper.toOut(newsService.getAllNews(pageable)));
    }

    /**
     * Ищет новости по частичному совпадению в заголовке или тексте.
     *
     * @param title часть заголовка для поиска (необязательно)
     * @param text часть текста для поиска (необязательно)
     * @param page номер страницы (по умолчанию 0)
     * @param size размер страницы (по умолчанию 10)
     * @return страница с найденными новостями {@link NewsDtoOut}
     * @apiNote Пример:
     * <pre>GET /api/news/findNewsByTitleTextLike?title=погода&text=дождь&page=0&size=5</pre>
     * @response code 200 Успешный запрос
     */
    @GetMapping("/findNewsByTitleTextLike")
    public ResponseEntity<Page<NewsDtoOut>> findNewsByTitleTextLike(
            @RequestParam(defaultValue = " ") String title,
            @RequestParam(defaultValue = " ") String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(newsMapper.toOut(newsService.findByTitleTextLike(title, text, pageable)));
    }

    /**
     * Создает новую новость.
     *
     * @param request DTO с данными новости (заголовок, текст)
     * @param customUserDetails данные аутентифицированного пользователя
     * @return созданная новость
     * <ul>
     *   <li>DEBUG: попытка создания.</li>
     *   <li>INFO: успешное создание.</li>
     * </ul>
     * @apiNote Пример тела запроса:
     * <pre>
     * {
     *   "title": "Новая погода",
     *   "text": "Ожидается сильный дождь..."
     * }
     * </pre>
     * @response code 201 Новость создана
     * @response code 400 Неверные данные
     * @response code 401 Требуется аутентификация
     */
    @PostMapping
    public ResponseEntity<NewsDtoOut> createNews(
            @RequestBody NewsDtoIn request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.debug("Creating new news by user: {}", customUserDetails.getUsername());
        News savedNews = newsService.persist(newsMapper.fromIn(request));
        log.info("News created successfully with id: {}", savedNews.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.toOut(savedNews));
    }

    /**
     * Обновляет существующую новость.
     *
     * @param id ID новости
     * @param request обновленные данные (заголовок, текст)
     * @param customUserDetails данные аутентифицированного пользователя
     * @return обновленная новость
     * <ul>
     *   <li>DEBUG: попытка обновления.</li>
     *   <li>INFO: успешное обновление.</li>
     * </ul>
     * @apiNote Пример:
     * <pre>
     * PUT /api/news/123
     * {
     *   "title": "Обновленный заголовок",
     *   "text": "Обновленный текст новости..."
     * }
     * </pre>
     * @response code 200 Успешное обновление
     * @response code 403 Доступ запрещен
     * @response code 404 Новость не найдена
     */
    @PutMapping("/{id}")
    public ResponseEntity<NewsDtoOut> updateNews(
            @PathVariable Long id,
            @RequestBody NewsDtoIn request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.debug("Updating news with id: {} by user: {}", id, customUserDetails.getUsername());
        News savedNews = newsService.updateNews(id, newsMapper.fromIn(request),
                customUserDetails.getId(), customUserDetails.getAuthorities().iterator().next().toString());
        log.info("News with id {} updated successfully", id);
        return ResponseEntity.ok(newsMapper.toOut(savedNews));
    }

    /**
     * Удаляет новость.
     *
     * @param id ID новости
     * @param customUserDetails данные аутентифицированного пользователя
     * <ul>
     *   <li>DEBUG: попытка удаления.</li>
     *   <li>INFO: успешное удаление.</li>
     * </ul>
     * @apiNote Пример:
     * <pre>DELETE /api/news/123</pre>
     * @response code 204 Новость удалена
     * @response code 403 Доступ запрещен
     * @response code 404 Новость не найдена
     */
    @DeleteMapping("/{id}")
    public void deleteNews(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.debug("Deleting news with id: {} by user: {}", id, customUserDetails.getUsername());
        newsService.delete(id, customUserDetails.getId(), customUserDetails.getAuthorities().iterator().next().toString());
        log.info("News with id {} deleted successfully", id);
    }
}
