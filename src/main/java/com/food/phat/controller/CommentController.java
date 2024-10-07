package com.food.phat.controller;

import com.food.phat.dto.comment.*;
import com.food.phat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/restaurants/{restaurantId}/comments/{commentId}")
    public ResponseEntity<?> createCommentRestaurant(@RequestBody CommentRestaurantPost commentPost) {
        commentService.createComment(commentPost);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/products/{productId}/comments/{commentId}")
    public ResponseEntity<?> createCommentProduct(@RequestBody CommentProductPost commentPost) {
        commentService.createComment(commentPost);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/comments")
    public ResponseEntity<?> createCommentOnOthers(@RequestBody CommentPost commentPost) {
        commentService.createComment(commentPost);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComments(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restaurants/{restaurantId}/comments")
    public ResponseEntity<CommentRestaurantResponse> getRestaurantComments(@PathVariable Integer restaurantId) {
        commentService.getRestaurantComment(restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/comments")
    public ResponseEntity<CommentProductResponse> getProductComments(@PathVariable Integer productId) {
        commentService.getProductComment(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comments/{parentCommentId}")
    public ResponseEntity<CommentResponse> getCommentLists(@PathVariable Integer parentCommentId) {
        CommentResponse commentResponse = commentService.getComment(parentCommentId);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> modifyComment(@RequestBody CommentPut commentPut) {
        commentService.modifyComment(commentPut);
        return new ResponseEntity<>(HttpStatus.OK);
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
 *  CommentPost
 *
 *  CommentRestaurantResponse
 *  {
 *      parentId: 123,
 *      [
 *          {
 *                 ID, starCount, content, isHavingChildren
 *          },
 *          {
 *              ...
 *          },
 *          ...
 *      ]
 *  }
 *  CommentProductResponse
 * {
 *      parentId: 123,
 *      [
 *          {
 *                 ID, starCount, content, isHavingChildren
 *          },
 *          {
 *              ...
 *          },
 *          ...
 *      ]
 *  }
 *  CommentLevelResponse
 * {
 *      parentId: 123,
 *      [
 *          {
 *              ID, content, isHavingChildren
 *          },
 *          {
 *              ...
 *          },
 *          ....
 *      ]
 * }
 *
 *
 *
 *
 *
 *
 *
 *
 */





