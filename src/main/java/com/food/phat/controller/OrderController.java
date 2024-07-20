package com.food.phat.controller;


import com.food.phat.dto.request.OrderRequest;
import com.food.phat.dto.response.CartResponse;
import com.food.phat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<CartResponse> placeOrder(List<OrderRequest> orderRequests) {
        orderService.placeOrder(orderRequests);
        return null;
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<CartResponse> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return null;
    }
}









