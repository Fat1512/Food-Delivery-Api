package com.food.phat.service;

import com.food.phat.dto.cart.CartRequest;
import com.food.phat.dto.cart.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse getCart(Integer userId);
    void deleteCartItem(List<Integer> cartDetailId);
    void saveOrUpdateCartItem(CartRequest cartRequest);
}
