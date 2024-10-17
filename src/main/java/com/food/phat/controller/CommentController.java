package com.food.phat.controller;

import com.food.phat.dto.MessageResponse;
import com.food.phat.dto.comment.*;
import com.food.phat.service.CommentService;
import com.food.phat.utils.ApiResponseMessage;
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
    public ResponseEntity<MessageResponse> createCommentRestaurant(@RequestBody CommentRestaurantPost commentPost) {
        commentService.createComment(commentPost);

        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PostMapping("/products/{productId}/comments/{commentId}")
    public ResponseEntity<MessageResponse> createCommentProduct(@RequestBody CommentProductPost commentPost) {
        commentService.createComment(commentPost);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);    }

    @PostMapping("/comments")
    public ResponseEntity<MessageResponse> createCommentOnOthers(@RequestBody CommentPost commentPost) {
        commentService.createComment(commentPost);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<MessageResponse> deleteComments(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_DELETED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);    }

    @GetMapping("/restaurants/{restaurantId}/comments")
    public ResponseEntity<MessageResponse> getRestaurantComments(@PathVariable Integer restaurantId) {
        CommentRestaurantResponse commentRestaurantResponse = commentService.getRestaurantComment(restaurantId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(commentRestaurantResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/comments")
    public ResponseEntity<MessageResponse> getProductComments(@PathVariable Integer productId) {
        CommentProductResponse commentProductResponse = commentService.getProductComment(productId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(commentProductResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/comments/{parentCommentId}")
    public ResponseEntity<MessageResponse> getCommentLists(@PathVariable Integer parentCommentId) {
        CommentResponse commentResponse = commentService.getComment(parentCommentId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(commentResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<MessageResponse> modifyComment(@RequestBody CommentPut commentPut) {
        commentService.modifyComment(commentPut);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_UPDATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
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





