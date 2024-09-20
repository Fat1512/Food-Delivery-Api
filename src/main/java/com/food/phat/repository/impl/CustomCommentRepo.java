package com.food.phat.repository.impl;

import com.food.phat.entity.Comment;
import com.food.phat.entity.CommentProduct;
import com.food.phat.entity.CommentRestaurant;

public interface CustomCommentRepo {
    Comment saveComment(Comment comment, Integer leftParent, Integer rightParent);
    CommentProduct saveComment(CommentProduct comment, Integer leftParent, Integer rightParent);
    CommentRestaurant saveComment(CommentRestaurant comment, Integer leftParent, Integer rightParent);
}
