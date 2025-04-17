package com.boic.testTask.news;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class News {
    private Long id;
    private String title;
    private String text;
    private LocalDateTime creationDate;
    private LocalDateTime lastEditDate;
    private Long insertedById;
    private Long updatedById;
}
