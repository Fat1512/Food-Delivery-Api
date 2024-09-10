package com.food.phat.mapstruct;

import com.food.phat.dto.ModifierGroupDTO;
import com.food.phat.dto.response.CartItemResponse;
import com.food.phat.dto.response.CartResponse;
import com.food.phat.dto.response.RestaurantCartResponse;
import com.food.phat.entity.Cart;
import com.food.phat.entity.CartItem;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
@DecoratedWith(CartDecorator.class)
public interface CartMapper {
    CartResponse toDto(Cart cart);
    List<CartResponse.CartItemGroup> cartItemToCartItemGroupDto(List<CartItem> cartItems);
}

@Mapper
abstract class CartDecorator implements CartMapper {

    @Qualifier("delegate")
    @Autowired
    private CartMapper delegate;

    private final RestaurantMapper restaurantMapper = Mappers.getMapper(RestaurantMapper.class);

    @Autowired
    private CartItemMapper cartItemMapper;
//    = Mappers.getMapper(CartItemMapper.class);

    @Override
    public CartResponse toDto(Cart cart) {
        CartResponse cartResponse = delegate.toDto(cart);
        cartResponse.setList(cartItemToCartItemGroupDto(cart.getCartItem()));
        return cartResponse;
    }


    public List<CartResponse.CartItemGroup> cartItemToCartItemGroupDto(List<CartItem> cartItems) {
        Map<Integer, RestaurantCartResponse> restaurantMp = new HashMap<>();
        Map<Integer, List<CartItemResponse>> cartItemResponseMp = new HashMap<>();

        cartItems.forEach(cartItem -> {
            CartItemResponse cartItemResponse = cartItemMapper.toDto(cartItem);
            RestaurantCartResponse restaurantCartResponse = restaurantMapper
                    .toRestaurantCartResponseDto(cartItem.getProduct().getRestaurant());

            restaurantMp.putIfAbsent(restaurantCartResponse.getRestaurantId(), restaurantCartResponse);
            cartItemResponseMp.putIfAbsent(restaurantCartResponse.getRestaurantId(), new ArrayList<>());

            cartItemResponseMp.get(restaurantCartResponse.getRestaurantId()).add(cartItemResponse);
        });
        return restaurantMp.entrySet().stream().map(entry ->
                new CartResponse.CartItemGroup(entry.getValue(), cartItemResponseMp.get(entry.getKey()))).toList();
    }
}