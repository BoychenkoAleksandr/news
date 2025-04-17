package com.boic.testTask.comments;

import com.boic.testTask.news.News;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comments {
    private Long id;
    private String text;
    private LocalDateTime creationDate;
    private LocalDateTime lastEditDate;
    private Long insertedById;
    private Long updatedById;
    private News news;
}
