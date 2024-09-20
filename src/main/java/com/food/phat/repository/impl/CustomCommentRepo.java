package com.food.phat.repository.impl;

import com.food.phat.entity.Comment;
import com.food.phat.entity.CommentProduct;
import com.food.phat.entity.CommentRestaurant;

import java.util.List;

public interface CustomCommentRepo {
    Comment saveComment(Comment comment, Integer leftParent, Integer rightParent);
    CommentProduct saveComment(CommentProduct comment);
    CommentRestaurant saveComment(CommentRestaurant comment);
    void deleteComment(Integer commentId);
    List<Comment> getCommentByLevel(Integer parentId);
}
