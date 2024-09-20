package com.food.phat.mapstruct.comment;

import com.food.phat.dto.comment.CommentPost;
import com.food.phat.dto.comment.CommentProductPost;
import com.food.phat.dto.comment.CommentRestaurantPost;
import com.food.phat.entity.Comment;
import com.food.phat.entity.CommentProduct;
import com.food.phat.entity.CommentRestaurant;
import com.food.phat.mapstruct.comment.decorator.CommentDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(CommentDecorator.class)
public interface CommentMapper {
    Comment toEntity(CommentPost commentPost);
    CommentRestaurant toEntity(CommentRestaurantPost commentPost);
    CommentProduct toEntity(CommentProductPost commentPost);
}

