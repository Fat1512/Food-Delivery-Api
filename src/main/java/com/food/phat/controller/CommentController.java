package com.food.phat.controller;

import com.food.phat.dto.comment.CommentPost;
import com.food.phat.dto.comment.CommentProductPost;
import com.food.phat.dto.comment.CommentRestaurantPost;
import com.food.phat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> createCommentRestaurant(@RequestBody CommentRestaurantPost commentPost) {
        commentService.createComment(commentPost);
        return null;
    }

    @PostMapping("/products")
    public ResponseEntity<?> createCommentProduct(@RequestBody CommentProductPost commentPost) {
        commentService.createComment(commentPost);
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<?> createCommentOnOthers(@RequestBody CommentPost commentPost) {
        commentService.createComment(commentPost);
        return null;
    }
}


/**
 *
 *
 *
 *  separate commentPOST DTO
 *
 *  response returns 1-level comment list with isHavingChildren included
 *  Check node depth to retrieve the next level
 *
 *  CommentRestaurantPost
 *  CommentProductPost
 *  CommentReplyPost
 *
 *  CommentRestaurantResponse
 *  CommentProductResponse
 *  CommentLevelResponse
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */





