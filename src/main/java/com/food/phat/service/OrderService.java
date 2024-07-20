package com.food.phat.service;

import com.food.phat.dto.request.OrderRequest;

import java.util.List;

public interface OrderService {
    void placeOrder(List<OrderRequest> orderRequests);
    void deleteOrder(Integer orderId);

}
