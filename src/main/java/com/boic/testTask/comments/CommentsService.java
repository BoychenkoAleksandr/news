package com.boic.testTask.comments;

import com.boic.testTask.common.CrudService;
import com.boic.testTask.common.JpaMapper;
import com.boic.testTask.common.Role;
import com.boic.testTask.exception.AuthorException;
import com.boic.testTask.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для работы с комментариями.
 * <p>
 * Реализует CRUD-операции для комментариев с проверкой прав доступа.
 * Поддерживает спецификации для расширенного поиска.
 * </p>
 *
 * @author Boichenko
 * @version 1.0
 * @see CrudService
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentsService implements CrudService<Comments, CommentsJpa> {
    private final CommentsJpaMapper commentsJpaMapper;
    private final CommentsRepository commentsRepository;
    @Override
    public JpaRepository<CommentsJpa, Long> getRepository() {
        return commentsRepository;
    }

    @Override
    public JpaSpecificationExecutor<CommentsJpa> getExecutor() {
        return commentsRepository;
    }

    @Override
    public JpaMapper<Comments, CommentsJpa> getMapper() {
        return commentsJpaMapper;
    }

    /**
     * Обновляет текст комментария с проверкой прав доступа.
     * <p>
     * Редактировать комментарий может только автор или администратор.
     * </p>
     *
     * @param id ID комментария
     * @param text обновленный текст комментария
     * @param userId ID текущего пользователя
     * @param role роль текущего пользователя
     * @return обновленный комментарий
     * @implNote Логирование:
     * <ul>
     *   <li>ERROR: при попытке редактирования без прав</li>
     * </ul>
     */
    @Transactional
    public Comments updateComments(Long id, String text, Long userId, String role) {
        final Comments comments;
        try {
            comments = this.getById(id);
        } catch (NotFoundException e) {
            throw new NotFoundException("Not found comments with id: " + id);
        }

        if (!comments.getInsertedById().equals(userId) && !role.equals(Role.ROLE_ADMIN.toString())) {
            log.error("Insufficient rights for this action");
            throw new AuthorException("You are not the author of this comments");
        }
        comments.setText(text);
        return this.persist(comments);
    }

    /**
     * Удаляет комментарий с проверкой прав доступа.
     * <p>
     * Удалять комментарий может только автор или администратор.
     * </p>
     *
     * @param id ID комментария
     * @param userId ID текущего пользователя
     * @param role роль текущего пользователя
     * @throws RuntimeException если комментарий не найден
     * @implNote Логирование:
     * <ul>
     *   <li>ERROR: при попытке удаления без прав</li>
     * </ul>
     */
    @Transactional
    public void delete(Long id, Long userId, String role) {
        final Comments comments;
        try {
            comments = this.getById(id);
        } catch (NotFoundException e) {
            throw new NotFoundException("Not found comments with id: " + id);
        }

        if (!comments.getInsertedById().equals(userId) && !role.equals(Role.ROLE_ADMIN.toString())) {
            log.error("Insufficient rights for this action");
            throw new AuthorException("You are not the author of this comments");
        }

        commentsRepository.delete(commentsJpaMapper.toJpaEntity(comments));
    }

    /**
     * Получает все комментарии для указанной новости с пагинацией.
     *
     * @param newsId ID новости
     * @param pageable параметры пагинации
     * @return страница с комментариями
     * @implSpec Использует {@link CommentsSpecification} для фильтрации по ID новости
     */
    public Page<Comments> getAllCommentsByNewsId(Long newsId, Pageable pageable) {
        return findBySpec(CommentsSpecification.findByNewsId(newsId), pageable);
    }
}
