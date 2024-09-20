package com.food.phat.dto.comment;

import lombok.Data;


@Data
public class CommentProductPost {
    private String content;
    private Integer starCount;
    private Integer productId;
    private Integer userId;
}
