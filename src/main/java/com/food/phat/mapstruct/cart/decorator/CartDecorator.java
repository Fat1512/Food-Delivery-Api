package com.food.phat.mapstruct.cart.decorator;

import com.food.phat.dto.cart.CartItemResponse;
import com.food.phat.dto.cart.CartResponse;
import com.food.phat.dto.restaurant.RestaurantCheckoutResponse;
import com.food.phat.entity.Cart;
import com.food.phat.entity.CartItem;
import com.food.phat.mapstruct.restaurant.RestaurantMapper;
import com.food.phat.mapstruct.cart.CartItemMapper;
import com.food.phat.mapstruct.cart.CartMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public abstract class CartDecorator implements CartMapper {

    @Qualifier("delegate")
    @Autowired
    private CartMapper delegate;
    @Autowired
    private RestaurantMapper restaurantMapper;
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public CartResponse toDto(Cart cart) {
        CartResponse cartResponse = delegate.toDto(cart);
        cartResponse.setList(cartItemToCartItemGroupDto(cart.getCartItem()));
        return cartResponse;
    }


    private List<CartResponse.CartItemGroup> cartItemToCartItemGroupDto(List<CartItem> cartItems) {
        Map<Integer, RestaurantCheckoutResponse> restaurantMp = new HashMap<>();
        Map<Integer, List<CartItemResponse>> cartItemResponseMp = new HashMap<>();

        cartItems.forEach(cartItem -> {
            CartItemResponse cartItemResponse = cartItemMapper.toDto(cartItem);
            RestaurantCheckoutResponse restaurantCheckoutResponse = restaurantMapper
                    .toRestaurantCheckoutResponse(cartItem.getProduct().getRestaurant());

            restaurantMp.putIfAbsent(restaurantCheckoutResponse.getRestaurantId(), restaurantCheckoutResponse);
            cartItemResponseMp.putIfAbsent(restaurantCheckoutResponse.getRestaurantId(), new ArrayList<>());

            cartItemResponseMp.get(restaurantCheckoutResponse.getRestaurantId()).add(cartItemResponse);
        });
        return restaurantMp.entrySet().stream().map(entry ->
                new CartResponse.CartItemGroup(entry.getValue(), cartItemResponseMp.get(entry.getKey()))).toList();
    }
}
