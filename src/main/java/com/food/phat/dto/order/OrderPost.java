package com.food.phat.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderPost {
    private Integer customerAddressId;
    private float shippingFee;
    private List<OrderItemPost> orderItems;
}
