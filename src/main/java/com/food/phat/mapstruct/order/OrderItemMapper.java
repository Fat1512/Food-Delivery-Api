package com.food.phat.mapstruct.order;

import com.food.phat.dto.order.OrderItemPost;
import com.food.phat.dto.order.OrderItemResponse;
import com.food.phat.entity.*;
import com.food.phat.mapstruct.order.decorator.OrderItemDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderItemDecorator.class)
public interface OrderItemMapper {
    @Mapping(target="price", source="product.price")
    @Mapping(target="thumbnail", source="product.thumbnail")
    @Mapping(target="name", source="product.name")
    @Mapping(target="productId", source="product.productId")
    @Mapping(target="restaurantId", source="product.restaurant.restaurantId")
    @Mapping(target="status", source="product.status")
    @Mapping(target = "modifierGroups", ignore = true)
    OrderItemResponse toDto(OrderItem orderItem);
    OrderItem toEntity(OrderItemPost orderItem);
}

