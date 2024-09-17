package com.food.phat.mapstruct.order;

import com.food.phat.dto.order.OrderCancelPost;
import com.food.phat.entity.OrderCancel;
import com.food.phat.mapstruct.order.decorator.OrderCancelDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderCancelDecorator.class)
public interface OrderCancelMapper {
    OrderCancel toEntity(OrderCancelPost orderCancelPost);
}

