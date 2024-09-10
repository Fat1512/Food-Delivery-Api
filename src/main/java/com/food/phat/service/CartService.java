package com.food.phat.service;

import com.food.phat.dto.request.CartRequest;
import com.food.phat.dto.response.CartResponse;
import com.food.phat.entity.Cart;

import java.util.List;

public interface CartService {
    CartResponse getCart(Integer userId);
    void deleteCartItem(List<Integer> cartDetailId);
    void saveOrUpdateCartItem(CartRequest cartRequest);
}
