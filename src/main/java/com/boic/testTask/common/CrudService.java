package com.boic.testTask.common;

import com.boic.testTask.exception.NotFoundException;
import com.boic.testTask.news.NewsJpa;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface CrudService<T, J> {

    JpaRepository<J, Long> getRepository();

    JpaSpecificationExecutor<J> getExecutor();

    JpaMapper<T, J> getMapper();

    default Specification<J> extendSpec(Specification<J> spec) {
        return spec;
    }

    default Page<T> findBySpec(Specification<J> spec, Pageable pageable, JpaMapper<T, J> jpaMapper) {
        Specification<J> specification = extendSpec(spec);
        return jpaMapper.fromJpaEntity(
                getExecutor().findAll(specification, pageable));
    }

    default Page<T> findBySpec(Specification<J> spec, Pageable pageable) {
        return findBySpec(spec, pageable, getMapper());
    }

    @Transactional
    default T persist(T entity) {
        return getMapper().fromJpaEntity(
                getRepository().save(
                        getMapper().toJpaEntity(entity)));
    }

    default T getById(Long id) {
        return getMapper().fromJpaEntity(
                getRepository().findById(id)).orElseThrow(() -> new NotFoundException("Not found with id: " + id));
    }
}
