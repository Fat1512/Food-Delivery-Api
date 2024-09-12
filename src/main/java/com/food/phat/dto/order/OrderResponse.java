package com.food.phat.dto.order;

import com.food.phat.dto.customer.CustomerAddressDTO;
import com.food.phat.dto.restaurant.RestaurantCheckoutResponse;
import com.food.phat.entity.OrderStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {
    private int orderId;
    private double shippingFee;
    private OrderStatus status;
    private Date orderTime;
    private CustomerAddressDTO customerAddress;
    private RestaurantCheckoutResponse restaurant;
    private List<OrderItemResponse> orderItems = new ArrayList<>();

    public void addOrderItemResponse(OrderItemResponse orderItemResponse) {
        orderItems.add(orderItemResponse);
    }
}
