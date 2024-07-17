package com.food.phat.repository.custom;

import com.food.phat.dto.CartResponse;

public interface CustomCartRepo {
    CartResponse getCart(Integer userId);
}
