package com.food.phat.service;

import com.food.phat.dto.comment.*;

public interface CommentService {
    void createComment(CommentPost commentPost);
    void createComment(CommentRestaurantPost commentPost);
    void createComment(CommentProductPost commentPost);
    void deleteComment(Integer commentId);
    CommentRestaurantResponse getRestaurantComment(Integer restaurantId);
    CommentProductResponse getProductComment(Integer productId);
    CommentResponse getComment(Integer parentId);
    void modifyComment(CommentPut commentPut);
}
