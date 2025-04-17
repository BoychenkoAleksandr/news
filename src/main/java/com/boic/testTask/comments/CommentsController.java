package com.boic.testTask.comments;

import com.boic.testTask.configuration.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

/**
 * REST-контроллер для работы с комментариями.
 * <p>
 * Предоставляет CRUD-операции для комментариев с пагинацией и проверкой прав доступа.
 * Требует аутентификации для создания/изменения/удаления.
 * </p>
 *
 * @author Boichenko
 * @version 1.0
 */
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentsController {
    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;

    /**
     * Получает комментарий по ID.
     *
     * @param id ID комментария (обязательно)
     * @return {@link CommentsDtoOut} данные комментария (id, текст комментария, id новости)
     * @apiNote Пример:
     * <pre>GET /api/comments/123</pre>
     * @response code 200 Комментарий найден
     * @response code 404 Комментарий не существует
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommentsDtoOut> getCommentsById(@PathVariable Long id) {
        log.debug("Fetching comments by id: {}", id);
        Comments findComments = commentsService.getById(id);
        log.debug("Successfully fetched comments with id: {}", id);
        return ResponseEntity.ok(commentsMapper.toOut(findComments));
    }

    /**
     * Получает все комментарии для новости с пагинацией.
     *
     * @param newsId ID новости (обязательно)
     * @param page номер страницы (по умолчанию 0)
     * @param size размер страницы (по умолчанию 10)
     * @return страница с {@link CommentsDtoOut}
     * @apiNote Пример:
     * <pre>GET /api/comments/getAllCommentsByNewsId/1?page=0&size=5</pre>
     * @response code 200 Успешный запрос
     */
    @GetMapping("/getAllCommentsByNewsId/{newsId}")
    public ResponseEntity<Page<CommentsDtoOut>> getAllCommentsByNewsId(
            @PathVariable Long newsId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(commentsMapper.toOut(commentsService.getAllCommentsByNewsId(newsId, pageable)));
    }

    /**
     * Создает новый комментарий.
     *
     * @param request DTO с данными комментария (текст комментария, id новости)
     * @param customUserDetails данные аутентифицированного пользователя
     * @return созданный комментарий
     * <ul>
     *   <li>DEBUG: попытка создания.</li>
     *   <li>INFO: успешное создание.</li>
     * </ul>
     * @apiNote Пример тела запроса:
     * <pre>
     * {
     *   "text": "Отличная статья!",
     *   "newsId": 1
     * }
     * </pre>
     * @response code 201 Комментарий создан
     * @response code 400 Неверные данные
     * @response code 401 Требуется аутентификация
     */
    @PostMapping
    public ResponseEntity<CommentsDtoOut> createComments(
            @RequestBody CommentsDtoIn request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.debug("Creating new comments by user: {}", customUserDetails.getUsername());
        Comments savedComments = commentsService.persist(commentsMapper.fromIn(request));
        log.info("Comments created successfully with id: {}", savedComments.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentsMapper.toOut(savedComments));
    }

    /**
     * Обновляет существующий комментарий.
     *
     * @param id ID комментария
     * @param request обновленные данные (текст комментария, id новости)
     * @param customUserDetails данные аутентифицированного пользователя
     * @return обновленный комментарий {@link CommentsDtoOut}
     * <ul>
     *   <li>DEBUG: попытка обновления.</li>
     *   <li>INFO: успешное обновление.</li>
     * </ul>
     * @apiNote Пример:
     * <pre>
     * PUT /api/comments/123
     * {
     *   "text": "Обновленный комментарий"
     * }
     * </pre>
     * @response code 200 Успешное обновление
     * @response code 403 Доступ запрещен
     * @response code 404 Комментарий не найден
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommentsDtoOut> updateComments(
            @PathVariable Long id,
            @RequestBody CommentsDtoIn request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.debug("Updating comments with id: {} by user: {}", id, customUserDetails.getUsername());
        Comments savedComments = commentsService.updateComments(id, request.getText(),
                customUserDetails.getId(), customUserDetails.getAuthorities().iterator().next().toString());
        log.info("Comments with id {} updated successfully", id);
        return ResponseEntity.ok(commentsMapper.toOut(savedComments));
    }

    /**
     * Удаляет комментарий.
     *
     * @param id ID комментария
     * @param customUserDetails данные аутентифицированного пользователя
     * <ul>
     *   <li>DEBUG: попытка удаления.</li>
     *   <li>INFO: успешное удаление.</li>
     * </ul>
     * @apiNote Пример:
     * <pre>DELETE /api/comments/123</pre>
     * @response code 204 Комментарий удален
     * @response code 403 Доступ запрещен
     * @response code 404 Комментарий не найден
     */
    @DeleteMapping("/{id}")
    public void deleteComments(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.debug("Deleting comments with id: {} by user: {}", id, customUserDetails.getUsername());
        commentsService.delete(id, customUserDetails.getId(), customUserDetails.getAuthorities().iterator().next().toString());
        log.info("Comments with id {} deleted successfully", id);
    }
}
