package com.food.phat.service;

import com.food.phat.dto.request.cart.CartRequest;
import com.food.phat.dto.response.cart.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse getCart(Integer userId);
    void deleteCartItem(List<Integer> cartDetailId);
    void updateCartItem(CartRequest cartRequest);
    void saveCartItem(CartRequest cartRequest);

}
