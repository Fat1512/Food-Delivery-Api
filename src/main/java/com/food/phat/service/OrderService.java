package com.food.phat.service;

import com.food.phat.dto.request.order.OrderRequest;
import com.food.phat.dto.response.order.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getOrders(Integer userId);
    void placeOrder(List<OrderRequest> orderRequests);
    void modifyOrderStatus(Integer orderId, String status);
}
