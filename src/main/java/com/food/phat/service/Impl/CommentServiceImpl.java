package com.food.phat.service.Impl;

import com.food.phat.dto.comment.*;
import com.food.phat.entity.Comment;
import com.food.phat.entity.CommentProduct;
import com.food.phat.entity.CommentRestaurant;
import com.food.phat.mapstruct.comment.CommentMapper;
import com.food.phat.repository.CommentProductRepository;
import com.food.phat.repository.CommentRepository;
import com.food.phat.repository.CommentRestaurantRepository;
import com.food.phat.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentRestaurantRepository commentRestaurantRepository;
    private final CommentProductRepository productCommentRepository;

    private final CommentMapper commentMapper;
    private final CommentProductRepository commentProductRepository;

    @Autowired
    public CommentServiceImpl(
            CommentRepository commentRepository,
            CommentRestaurantRepository commentRestaurantRepository,
            CommentProductRepository productCommentRepository,
            CommentMapper commentMapper, CommentProductRepository commentProductRepository) {
        this.commentRepository = commentRepository;
        this.commentRestaurantRepository = commentRestaurantRepository;
        this.productCommentRepository = productCommentRepository;
        this.commentMapper = commentMapper;
        this.commentProductRepository = commentProductRepository;
    }

    @Override
    @Transactional
    public void createComment(CommentPost commentPost) {
        Comment comment = commentMapper.toEntity(commentPost);
        commentRepository.saveComment(comment,
                commentPost.getLeftParent(),
                commentPost.getRightParent());
    }

    @Override
    @Transactional
    public void createComment(CommentProductPost commentPost) {
        CommentProduct commentProduct = commentMapper.toEntity(commentPost);
        commentRepository.saveComment(commentProduct);
    }

    @Override
    @Transactional
    public void createComment(CommentRestaurantPost commentPost) {
        CommentRestaurant commentRestaurant = commentMapper.toEntity(commentPost);
        commentRepository.saveComment(commentRestaurant);
    }

    @Override
    @Transactional
    public void deleteComment(Integer commentId) {
        commentRepository.deleteComment(commentId);
    }

    @Transactional
    @Override
    public CommentRestaurantResponse getRestaurantComment(Integer restaurantId) {
        List<CommentRestaurant> comments = commentRestaurantRepository.findByRestaurant_RestaurantId(restaurantId);
        List<CommentRestaurantItemResponse> commentItemResponses = comments.stream().map(commentMapper::toDTO).toList();
        CommentRestaurantResponse commentRestaurantResponse = new CommentRestaurantResponse();
        commentRestaurantResponse.setComments(commentItemResponses);
        return commentRestaurantResponse;
    }

    @Transactional
    @Override
    public CommentProductResponse getProductComment(Integer productId) {
        List<CommentProduct> comments = commentProductRepository.findByProduct_ProductId(productId);
        List<CommentProductItemResponse> commentItemResponses = comments.stream().map(commentMapper::toDTO).toList();
        CommentProductResponse commentProductResponse = new CommentProductResponse();
        commentProductResponse.setComments(commentItemResponses);
        return commentProductResponse;
    }

    @Transactional
    @Override
    public CommentResponse getComment(Integer parentId) {
        List<Comment> commentList = commentRepository.getCommentByLevel(parentId);
        List<CommentItemResponse> commentItems = commentList.stream().map(commentMapper::toDTO).toList();
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setComments(commentItems);
        commentResponse.setParentId(parentId);
        return commentResponse;
    }
}
