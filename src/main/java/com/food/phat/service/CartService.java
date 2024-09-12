package com.food.phat.service;

import com.food.phat.dto.cart.CartItemRequest;
import com.food.phat.dto.cart.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse getCart(Integer userId);
    void deleteCartItem(List<Integer> cartDetailId, Integer userId);
    void saveCartItem(CartItemRequest cartItemRequest, Integer userId);
}
