package com.food.phat.service;

import com.food.phat.dto.order.OrderRequest;
import com.food.phat.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getOrders(Integer userId);
    OrderResponse getOrder(Integer orderId);
    void placeOrder(List<OrderRequest> orderRequests);
    void modifyOrderStatus(Integer orderId, String status);
}
