package com.food.phat.service;

import com.food.phat.dto.cart.CartItemPut;
import com.food.phat.dto.cart.CartItemPost;
import com.food.phat.dto.cart.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse getCart(Integer userId);
    void deleteCartItem(List<Integer> cartDetailId, Integer userId);
    void saveCartItem(CartItemPost cartItemPost, Integer userId);
    void updateCartItem(CartItemPut cartItemRequest, Integer userId);
}
