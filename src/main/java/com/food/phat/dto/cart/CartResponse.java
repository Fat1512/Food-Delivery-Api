package com.food.phat.dto.cart;

import com.food.phat.dto.restaurant.RestaurantCheckoutResponse;
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
    @Builder
    public static class CartItemGroup {
        private RestaurantCheckoutResponse restaurant;
        private List<CartItemResponse> cartItems = new ArrayList<>();

        public void addCartItemResponse(CartItemResponse cartItemResponse) {
            cartItems.add(cartItemResponse);
        }
    }
}















