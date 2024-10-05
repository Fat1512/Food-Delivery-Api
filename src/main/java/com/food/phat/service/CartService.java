package com.food.phat.service;

import com.food.phat.dto.cart.CartItemPut;
import com.food.phat.dto.cart.CartItemPost;
import com.food.phat.dto.cart.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse getCart(Integer userId) throws Exception;
    void deleteCartItem(List<Integer> cartDetailId);
    void saveCartItem(CartItemPost cartItemPost);
    void updateCartItem(CartItemPut cartItemRequest) throws Exception;
}
