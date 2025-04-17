package com.boic.testTask.comments;

import org.springframework.data.jpa.domain.Specification;

public class CommentsSpecification {
    public static Specification<CommentsJpa> findByNewsId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("news").get("id"), id);
    }
}
