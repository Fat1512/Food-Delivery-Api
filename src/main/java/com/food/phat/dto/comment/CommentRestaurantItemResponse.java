package com.food.phat.dto.comment;

import lombok.Data;

@Data
public class CommentRestaurantItemResponse {
    private Integer commentId;
    private Integer starCount;
    private String content;
    private Boolean isHavingChildren;
}
