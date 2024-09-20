package com.food.phat.mapstruct.comment;

import com.food.phat.dto.comment.*;
import com.food.phat.entity.Comment;
import com.food.phat.entity.CommentProduct;
import com.food.phat.entity.CommentRestaurant;
import com.food.phat.mapstruct.comment.decorator.CommentDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
@DecoratedWith(CommentDecorator.class)
public interface CommentMapper {
    Comment toEntity(CommentPost commentPost);
    CommentRestaurant toEntity(CommentRestaurantPost commentPost);
    CommentProduct toEntity(CommentProductPost commentPost);

    CommentRestaurantItemResponse toDTO(CommentRestaurant commentRestaurant);
    CommentProductItemResponse toDTO(CommentProduct commentProduct);
    CommentItemResponse toDTO(Comment comment);
}

