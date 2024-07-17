package com.food.phat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CartItemResponse {
    private int restaurantId;
    private String restaurantName;
    List<ProductResponse> products;
}
