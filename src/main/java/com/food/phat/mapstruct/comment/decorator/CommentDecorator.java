package com.food.phat.mapstruct.comment.decorator;

import com.food.phat.dto.comment.CommentPost;
import com.food.phat.dto.comment.CommentProductPost;
import com.food.phat.dto.comment.CommentRestaurantPost;
import com.food.phat.entity.Comment;
import com.food.phat.entity.CommentProduct;
import com.food.phat.entity.CommentRestaurant;
import com.food.phat.mapstruct.comment.CommentMapper;
import com.food.phat.repository.ProductRepository;
import com.food.phat.repository.RestaurantRepository;
import com.food.phat.repository.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public abstract class CommentDecorator implements CommentMapper {
    @Qualifier("delegate")
    @Autowired
    private CommentMapper delegate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Comment toEntity(CommentPost commentPost) {
        Comment comment = delegate.toEntity(commentPost);
        comment.setContent(commentPost.getContent());
        comment.setUser(userRepository.findById(commentPost.getUserId()).get());
        return comment;
    }

    @Override
    public CommentRestaurant toEntity(CommentRestaurantPost commentPost) {
        Comment comment = new Comment();
        comment.setContent(commentPost.getContent());
        comment.setUser(userRepository.findById(commentPost.getUserId()).get());

        CommentRestaurant restaurantComment = delegate.toEntity(commentPost);
        restaurantComment.setRestaurant(restaurantRepository.findById(commentPost.getRestaurantId()).get());
        restaurantComment.setStarCount(restaurantComment.getStarCount());
        restaurantComment.setComment(comment);
        return restaurantComment;
    }

    @Override
    public CommentProduct toEntity(CommentProductPost commentPost) {
        Comment comment = new Comment();
        comment.setContent(commentPost.getContent());
        comment.setUser(userRepository.findById(commentPost.getUserId()).get());

        CommentProduct productComment = delegate.toEntity(commentPost);
        productComment.setProduct(productRepository.findById(commentPost.getProductId()).get());
        productComment.setStarCount(productComment.getStarCount());
        productComment.setComment(comment);
        return productComment;
    }
}
