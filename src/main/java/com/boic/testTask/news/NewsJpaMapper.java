package com.boic.testTask.news;


import com.boic.testTask.common.DateMapper;
import com.boic.testTask.common.JpaMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = DateMapper.class)
public interface NewsMapper extends JpaMapper<NewsDtoOut, NewsJpa> {
    @Mapping(target = "title", source = "title")
    @Mapping(target = "text", source = "text")
    NewsDtoOut fromJpaEntity(NewsJpa jpaEntity);
}
