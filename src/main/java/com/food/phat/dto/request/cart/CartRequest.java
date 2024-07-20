package com.food.phat.dto.request.cart;


import lombok.Data;

@Data
public class CartRequest {
    private Integer cartId;
    private CartItemRequest cartItemRequest;
}
