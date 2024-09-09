package com.food.phat.dto.response;

import com.food.phat.dto.CustomerAddressDTO;
import com.food.phat.entity.OrderStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class OrderResponse {
    private int orderId;
    private float shippingFee;
    private OrderStatus status;
    private CustomerAddressDTO customerAddress;
    private Map<String, Object> restaurantInfo = new HashMap<>();
    private List<CartItemResponse> products;

    public void addCartItemResponse(CartItemResponse cartItemResponse) {
        if(products == null) products = new ArrayList<>();
        products.add(cartItemResponse);
    }
}
