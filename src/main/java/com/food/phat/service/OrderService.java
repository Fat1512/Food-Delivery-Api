package com.food.phat.service;

import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.order.OrderResponse;
import com.food.phat.entity.OrderStatus;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getOrders(Integer userId);
    OrderResponse getOrder(Integer orderId);
    void placeOrder(List<OrderPost> orderPosts, Integer userId);
    void modifyOrderStatus(Integer orderId, OrderStatus status);
}
