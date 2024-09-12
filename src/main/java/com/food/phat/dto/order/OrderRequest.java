package com.food.phat.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Integer orderId;
    private Integer restaurantId;
    private Integer customerAddressId;
    private float shippingFee;
    private List<OrderItemRequest> orderItems;
}