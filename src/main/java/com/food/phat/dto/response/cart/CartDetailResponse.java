package com.food.phat.dto.response.cart;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CartDetailResponse {
    private Map<String, Object> restaurantInfo = new HashMap<>();
    List<CartItemResponse> products;

    public void addCartItemResponse(CartItemResponse cartItemResponse) {
        if(products == null) products = new ArrayList<>();
        products.add(cartItemResponse);
    }
}
