package com.food.phat.service.Impl;

import com.food.phat.dto.CartResponse;
import com.food.phat.repository.CartRepository;
import com.food.phat.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public CartResponse getCart(Integer userId) {
        return cartRepository.getCart(userId);
    }
}
