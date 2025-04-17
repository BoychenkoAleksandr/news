package com.boic.testTask.comments;

import lombok.Data;

@Data
public class CommentsDtoOut {
    private Long id;
    private String text;
    private Long newsId;
}
