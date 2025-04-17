package com.boic.testTask.comments;

import com.boic.testTask.common.JpaMapper;
import com.boic.testTask.common.NewsOnlyId;
import com.boic.testTask.news.News;
import com.boic.testTask.news.NewsJpa;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {NewsOnlyId.class})
public interface CommentsJpaMapper extends JpaMapper<Comments, CommentsJpa> {
    @Mapping(target = "news", qualifiedByName = "NewsOnlyId")
    Comments fromJpaEntity(CommentsJpa jpaEntity);

    @Mapping(target = "news", qualifiedByName = "NewsJpaOnlyId")
    CommentsJpa toJpaEntity(Comments entity);
}


