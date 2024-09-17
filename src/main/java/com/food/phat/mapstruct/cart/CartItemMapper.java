package com.food.phat.mapstruct.cart;

import com.food.phat.dto.cart.CartItemPut;
import com.food.phat.dto.cart.CartItemPost;
import com.food.phat.dto.cart.CartItemResponse;
import com.food.phat.entity.*;
import com.food.phat.mapstruct.cart.decorator.CartItemDecorator;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
@DecoratedWith(CartItemDecorator.class)
public interface  CartItemMapper {
    @Mapping(target = "productId", source="product.productId")
    @Mapping(target = "restaurantId", source="product.restaurant.restaurantId")
    @Mapping(target = "name", source="product.name")
    @Mapping(target = "status", source="product.status")
    @Mapping(target = "thumbnail", source="product.thumbnail")
    @Mapping(target = "modifierGroups", ignore = true)
    CartItemResponse toDto(CartItem cartItem);

    CartItem toEntity(CartItemPost cartItemPost);

    @Mapping(target ="cartItemId", ignore = true)
    void updateEntity(CartItemPut cartItemPut, @MappingTarget CartItem cartItem);
}
















