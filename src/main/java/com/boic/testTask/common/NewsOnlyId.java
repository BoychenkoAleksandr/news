package com.boic.testTask.common;

import com.boic.testTask.news.News;
import com.boic.testTask.news.NewsJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface NewsOnlyId {
    @Named("NewsOnlyId")
    default News onlyId(NewsJpa jpaEntity) {
        if (jpaEntity == null)
            return null;
        News news = new News();
        news.setId(jpaEntity.getId());
        return news;
    }

    @Named("NewsJpaOnlyId")
    default NewsJpa onlyId(News entity) {
        if (entity == null)
            return null;
        NewsJpa newsJpa = new NewsJpa();
        newsJpa.setId(entity.getId());
        return newsJpa;
    }
}
