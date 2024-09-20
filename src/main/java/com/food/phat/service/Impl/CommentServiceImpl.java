package com.food.phat.service.Impl;

import com.food.phat.dto.comment.CommentPost;
import com.food.phat.dto.comment.CommentProductPost;
import com.food.phat.dto.comment.CommentRestaurantPost;
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

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentRestaurantRepository commentRestaurantRepository;
    private final CommentProductRepository productCommentRepository;

    private CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(
            CommentRepository commentRepository,
            CommentRestaurantRepository commentRestaurantRepository,
            CommentProductRepository productCommentRepository,
            CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentRestaurantRepository = commentRestaurantRepository;
        this.productCommentRepository = productCommentRepository;
        this.commentMapper = commentMapper;
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
        commentRepository.saveComment(commentProduct,
                commentPost.getLeftParent(),
                commentPost.getRightParent());
    }

    @Override
    @Transactional
    public void createComment(CommentRestaurantPost commentPost) {
        CommentRestaurant commentRestaurant = commentMapper.toEntity(commentPost);
        commentRepository.saveComment(commentRestaurant,
                commentPost.getLeftParent(),
                commentPost.getRightParent());
    }
}
