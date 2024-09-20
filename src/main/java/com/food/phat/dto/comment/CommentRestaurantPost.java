package com.food.phat.dto.comment;

import lombok.Data;

@Data
public class CommentRestaurantPost {
    private String content;
    private Integer starCount;
    private Integer restaurantId;
    private Integer userId;
}
