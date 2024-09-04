package com.food.phat.dto.response.cart;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
        private Map<String, String> restaurantInfo = new HashMap<>();
        private List<CartItemResponse> products;

        public void addCartItemResponse(CartItemResponse cartItemResponse) {
            if(products == null) products = new ArrayList<>();
            products.add(cartItemResponse);
        }
    }
}
