package com.food.phat.service;

import com.food.phat.dto.comment.CommentPost;
import com.food.phat.dto.comment.CommentProductPost;
import com.food.phat.dto.comment.CommentRestaurantPost;

public interface CommentService {
    void createComment(CommentPost commentPost);
    void createComment(CommentRestaurantPost commentPost);
    void createComment(CommentProductPost commentPost);
}
