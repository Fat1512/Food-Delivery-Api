package com.food.phat.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class CartItemResponse {
    private int restaurantId;
    private String restaurantName;
    List<CartDetailResponse> products;

    public void addCartDetailResponse(CartDetailResponse cartDetailResponse) {
        if(products == null) products = new ArrayList<>();
        products.add(cartDetailResponse);
    }
}
