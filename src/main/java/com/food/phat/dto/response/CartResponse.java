package com.food.phat.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Data
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CartItemGroup {
        private RestaurantCartResponse restaurant;
        private List<CartItemResponse> products = new ArrayList<>();

        public void addCartItemResponse(CartItemResponse cartItemResponse) {
            products.add(cartItemResponse);
        }
    }
}















