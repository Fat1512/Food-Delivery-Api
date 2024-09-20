package com.food.phat.controller;

import com.food.phat.dto.comment.*;
import com.food.phat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComments(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return null;
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<CommentRestaurantResponse> getRestaurantComments(@PathVariable Integer restaurantId) {
        commentService.getRestaurantComment(restaurantId);
        return null;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<CommentProductResponse> getProductComments(@PathVariable Integer productId) {
        commentService.getProductComment(productId);
        return null;
    }

    @GetMapping("/{parentCommentId}")
    public ResponseEntity<CommentResponse> getCommentLists(@PathVariable Integer parentCommentId) {
        CommentResponse commentResponse = commentService.getComment(parentCommentId);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<CommentResponse> modifyComment(@RequestBody CommentPut commentPut) {
        commentService.modifyComment(commentPut);
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





