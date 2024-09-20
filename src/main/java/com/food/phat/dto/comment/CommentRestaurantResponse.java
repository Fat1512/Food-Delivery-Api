package com.food.phat.dto.comment;

import lombok.Data;

import java.util.List;

@Data
public class CommentRestaurantResponse {
    private List<CommentRestaurantItemResponse> comments;
}
