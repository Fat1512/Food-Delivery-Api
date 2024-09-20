package com.food.phat.dto.comment;

import lombok.Data;


@Data
public class CommentPost {
    private String content;
    private Integer leftParent;
    private Integer rightParent;
    private Integer userId;
}
