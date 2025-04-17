package com.boic.testTask.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository  extends JpaRepository<CommentsJpa, Long>, JpaSpecificationExecutor<CommentsJpa> {
}
