package com.boic.test_task.news;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsJpa, Long> {
}
