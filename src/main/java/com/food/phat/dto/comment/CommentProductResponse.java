package com.food.phat.dto.comment;

import lombok.Data;

import java.util.List;

@Data
public class CommentProductResponse {
    private List<CommentProductItemResponse> comments;
}
