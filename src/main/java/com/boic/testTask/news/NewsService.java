package com.boic.testTask.news;

import com.boic.testTask.comments.Comments;
import com.boic.testTask.common.CrudService;
import com.boic.testTask.common.JpaMapper;
import com.boic.testTask.common.Role;
import com.boic.testTask.exception.AuthorException;
import com.boic.testTask.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * Сервис для работы с новостями.
 * <p>
 * Реализует CRUD-операции для новостей с проверкой прав доступа.
 * Поддерживает пагинацию и поиск по спецификациям.
 * </p>
 *
 * @author Boichenko
 * @version 1.0
 * @see CrudService
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService implements CrudService<News, NewsJpa> {
    private final NewsJpaMapper newsJpaMapper;
    private final NewsRepository newsRepository;

    @Override
    public JpaRepository<NewsJpa, Long> getRepository() {
        return newsRepository;
    }

    @Override
    public JpaMapper<News, NewsJpa> getMapper() {
        return newsJpaMapper;
    }

    @Override
    public JpaSpecificationExecutor<NewsJpa> getExecutor() {
        return newsRepository;
    }

    /**
     * Обновляет новость с проверкой прав доступа.
     * <p>
     * Редактировать новость может только автор или администратор.
     * Обновляет как текст, так и заголовок новости.
     * </p>
     *
     * @param id ID новости
     * @param request обновленные данные новости (текст, заголовок)
     * @param userId ID текущего пользователя
     * @param role роль текущего пользователя
     * @return обновленная новость
     * @implNote Логирование:
     * <ul>
     *   <li>ERROR: при попытке редактирования без прав</li>
     * </ul>
     */
    @Transactional
    public News updateNews(Long id, News request, Long userId, String role) {
        final News news;
        try {
            news = this.getById(id);
        } catch (NotFoundException e) {
            throw new NotFoundException("Not found news with id: " + id);
        }

        if (!news.getInsertedById().equals(userId) && !role.equals(Role.ROLE_ADMIN.toString())) {
            log.error("Insufficient rights for this action");
            throw new AuthorException("You are not the author of this news");
        }
        news.setText(request.getText());
        news.setTitle(request.getTitle());
        return this.persist(news);
    }

    /**
     * Удаляет новость с проверкой прав доступа.
     * <p>
     * Удалять новость может только автор или администратор.
     * </p>
     *
     * @param id ID новости
     * @param userId ID текущего пользователя
     * @param role роль текущего пользователя
     * @throws RuntimeException если новость не найдена
     * @implNote Логирование:
     * <ul>
     *   <li>ERROR: при попытке удаления без прав</li>
     * </ul>
     */
    @Transactional
    public void delete(Long id, Long userId, String role) {
        final News news;
        try {
            news = this.getById(id);
        } catch (NotFoundException e) {
            throw new NotFoundException("Not found news with id: " + id);
        }

        if (!news.getInsertedById().equals(userId) && !role.equals(Role.ROLE_ADMIN.toString())) {
            log.error("Insufficient rights for this action");
            throw new AuthorException("You are not the author of this news");
        }

        newsRepository.delete(newsJpaMapper.toJpaEntity(news));
    }

    /**
     * Получает все новости с пагинацией.
     *
     * @param pageable параметры пагинации
     * @return страница с новостями
     */
    public Page<News> getAllNews(Pageable pageable) {
        return newsJpaMapper.fromJpaEntity(newsRepository.findAll(pageable));
    }

    /**
     * Ищет новости по частичному совпадению в заголовке и тексте.
     *
     * @param title часть заголовка для поиска
     * @param text часть текста для поиска
     * @param page параметры пагинации
     * @return страница с найденными новостями
     * @implSpec Использует {@link NewsSpecifications} для создания критериев поиска
     */
    public Page<News> findByTitleTextLike(String title, String text, Pageable page) {
        return findBySpec(NewsSpecifications.findByTitle(title).and(NewsSpecifications.findByText(text)), page);
    }
}
