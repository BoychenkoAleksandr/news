package com.boic.testTask.news;

import org.springframework.data.jpa.domain.Specification;

public class NewsSpecifications {
    static Specification<NewsJpa> findByTitle(String title) {
        if (title == null || title.equals(" ")) return (root, query, cb) -> cb.and();
        return (root, query, cb) -> cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }

    static Specification<NewsJpa> findByText(String text) {
        if (text == null || text.equals(" ")) return (root, query, cb) -> cb.and();
        return (root, query, cb) -> cb.like(cb.upper(root.get("text")), "%" + text.toUpperCase() + "%");
    }
}
