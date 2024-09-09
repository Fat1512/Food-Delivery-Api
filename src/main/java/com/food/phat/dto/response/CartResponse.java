package com.food.phat.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Setter
@Getter
public class CartResponse {
    private int cartId;
    private List<CartItemGroup> list;

    public void addCartItem(CartItemGroup cartItemGroup) {
        if(list == null) list = new ArrayList<>();
        list.add(cartItemGroup);
    }

    @Setter
    @Getter
    private class CartItemGroup {
        @Autowired
        private RestaurantCartReponse restaurant;
        private List<CartItemResponse> products = new ArrayList<>();

        public void addCartItemResponse(CartItemResponse cartItemResponse) {
            products.add(cartItemResponse);
        }
    }
}
