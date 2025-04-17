package com.boic.testTask.news;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface NewsMapper {
    @Mapping(target = "title", source = "title")
    NewsDtoOut toOut(News entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "lastEditDate", ignore = true)
    @Mapping(target = "insertedById", ignore = true)
    @Mapping(target = "updatedById", ignore = true)
    News fromIn(NewsDtoIn entity);

    default Page<NewsDtoOut> toOut(Page<News> page) {
        return page.map(this::toOut);
    }
}
