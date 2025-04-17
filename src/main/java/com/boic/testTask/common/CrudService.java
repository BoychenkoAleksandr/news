package com.boic.test_task.common;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudService<T, J> {

    JpaRepository<J, Long> getRepository();

    JpaMapper<T, J> getMapper();

    default JpaMapper<T, J> getFullMapper() {
        return getMapper();
    }

    @Transactional
    default T persist(T entity) {
        return getMapper().fromJpaEntity(
                getRepository().save(
                        getMapper().toJpaEntity(entity)));
    }

    @Transactional
    default void delete(Long id) {
        getRepository().deleteById(id);
    }

    default Page<T> findAll(Pageable pageable) {
        return findAll(pageable, getMapper());
    }

    default Page<T> findAll(Pageable pageable, JpaMapper<T, J> mapper) {
        return mapper.fromJpaEntity(
                getRepository().findAll(pageable));
    }

    default List<T> findAll(Sort sort) {
        return getMapper().fromJpaEntity(
                getRepository().findAll(sort));
    }

    default List<T> findAll() {
        return findAll(getMapper());
    }

    default List<T> findAll(JpaMapper<T, J> mapper) {
        return mapper.fromJpaEntity(
                getRepository().findAll());
    }
}
