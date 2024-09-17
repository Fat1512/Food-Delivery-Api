package com.food.phat.mapstruct.cart;

import com.food.phat.dto.cart.CartResponse;
import com.food.phat.entity.Cart;
import com.food.phat.mapstruct.cart.decorator.CartDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(CartDecorator.class)
public interface CartMapper {
    CartResponse toDto(Cart cart);
}














