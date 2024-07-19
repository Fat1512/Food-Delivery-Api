package com.food.phat.service;

import com.food.phat.dto.request.CartDetailRequest;
import com.food.phat.dto.response.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse getCart(Integer cartId);
    void deleteCartItem(List<Integer> cartDetailId);
    void updateCartItem(CartDetailRequest cartDetailRequest);
}
