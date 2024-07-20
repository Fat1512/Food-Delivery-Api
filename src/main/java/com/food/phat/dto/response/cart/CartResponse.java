package com.food.phat.dto.response.cart;

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
    List<CartDetailResponse> list;

    public void addCartItem(CartDetailResponse cartDetailResponse) {
        if(list == null) list = new ArrayList<>();
        list.add(cartDetailResponse);
    }
}
