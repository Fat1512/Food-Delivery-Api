package com.food.phat.service;

import com.food.phat.dto.CartResponse;

public interface CartService {
    CartResponse getCart(Integer userId);
}
