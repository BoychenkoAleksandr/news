package com.boic.testTask.news;


import com.boic.testTask.common.JpaMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface NewsJpaMapper extends JpaMapper<News, NewsJpa> {
    @Mapping(target = "title", source = "title")
    News fromJpaEntity(NewsJpa jpaEntity);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "comments", ignore = true)
    NewsJpa toJpaEntity(News entity);
}
