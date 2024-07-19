package com.food.phat.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
public class CartResponse {
    int cartId;
    List<CartItemResponse> list;

    public void addCartItem(CartItemResponse cartItemResponse) {
        if(list == null) list = new ArrayList<>();
        list.add(cartItemResponse);
    }
}
