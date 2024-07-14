package com.food.phat.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class CartResponse {
    int cartId;
    List<CartItemResponse> cartItemResponse;
}
