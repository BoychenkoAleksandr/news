package com.boic.testTask.comments;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentsMapper {
    @Mapping(target = "newsId", source = "news.id")
    CommentsDtoOut toOut(Comments entity);

    @Mapping(target = "news.id", source = "newsId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "lastEditDate", ignore = true)
    @Mapping(target = "insertedById", ignore = true)
    @Mapping(target = "updatedById", ignore = true)
    Comments fromIn(CommentsDtoIn entity);

    default Page<CommentsDtoOut> toOut(Page<Comments> page) {
        return page.map(this::toOut);
    }
}
