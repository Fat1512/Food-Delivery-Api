package com.food.phat.mapstruct;

import com.food.phat.entity.Cart;
import com.food.phat.entity.Order;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(CartToOrderDecorator.class)
public interface CartToOrderMapper {
    Order toOrder(Cart cart);
}

@Mapper
abstract class CartToOrderDecorator implements CartToOrderMapper {

    @Override
    public Order toOrder(Cart cart) {
        return null;
    }
}