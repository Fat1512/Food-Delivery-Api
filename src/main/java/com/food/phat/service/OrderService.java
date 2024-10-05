package com.food.phat.service;

import com.food.phat.dto.order.OrderCancelPost;
import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.order.OrderResponse;
import com.food.phat.entity.OrderStatus;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getOrders(Integer userId) throws Exception;
    OrderResponse getOrder(Integer orderId) throws Exception;
    void placeOrder(List<OrderPost> orderPosts);
    void modifyOrderStatus(Integer orderId, OrderStatus status) throws Exception;
    void cancelOrder(OrderCancelPost orderCancelPost) throws Exception;
}
