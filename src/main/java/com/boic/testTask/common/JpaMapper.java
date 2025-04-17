package com.boic.test_task.common;

import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface JpaMapper<T, J> {

    default T fromJpaEntity(J jpaEntity) {
        return null;
    }

    default J toJpaEntity(T entity) {
        return null;
    }

    default void merge(T entity, @MappingTarget J jpaEntity) {}

    default List<J> toJpaEntity(List<T> entity) {
        return entity.stream().map(this::toJpaEntity).collect(Collectors.toList());
    }

    default List<T> fromJpaEntity(List<J> jpaEntity) {
        return jpaEntity.stream().map(this::fromJpaEntity).collect(Collectors.toList());
    }

    default Page<T> fromJpaEntity(Page<J> page) {
        return page.map(this::fromJpaEntity);
    }

    default Optional<T> fromJpaEntity(Optional<J> optional) {
        return optional.map(this::fromJpaEntity);
    }

}
