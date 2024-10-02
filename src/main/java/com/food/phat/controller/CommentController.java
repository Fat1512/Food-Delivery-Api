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
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/products")
    public ResponseEntity<?> createCommentProduct(@RequestBody CommentProductPost commentPost) {
        commentService.createComment(commentPost);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<?> createCommentOnOthers(@RequestBody CommentPost commentPost) {
        commentService.createComment(commentPost);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComments(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<CommentRestaurantResponse> getRestaurantComments(@PathVariable Integer restaurantId) {
        commentService.getRestaurantComment(restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<CommentProductResponse> getProductComments(@PathVariable Integer productId) {
        commentService.getProductComment(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{parentCommentId}")
    public ResponseEntity<CommentResponse> getCommentLists(@PathVariable Integer parentCommentId) {
        CommentResponse commentResponse = commentService.getComment(parentCommentId);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @PutMapping("/")
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





