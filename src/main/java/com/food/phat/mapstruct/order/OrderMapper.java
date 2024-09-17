package com.food.phat.mapstruct.order;


import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.order.OrderResponse;
import com.food.phat.entity.Order;
import com.food.phat.mapstruct.order.decorator.OrderDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderDecorator.class)
public interface OrderMapper {
    OrderResponse toDto(Order order);
    Order toEntity(OrderPost orderPost);
}


















