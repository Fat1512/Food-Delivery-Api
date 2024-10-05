package com.food.phat.controller;

import com.food.phat.dto.order.OrderCancelPost;
import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.cart.CartResponse;
import com.food.phat.dto.order.OrderResponse;
import com.food.phat.dto.order.OrderStatusPut;
import com.food.phat.entity.OrderStatus;
import com.food.phat.service.Impl.UserServiceImpl;
import com.food.phat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public OrderController(OrderService orderService, UserServiceImpl userServiceImpl) {
        this.orderService = orderService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users/{userId}/orders/")
    public ResponseEntity<List<OrderResponse>> getOrderByUser(@PathVariable Integer userId) throws Exception {
        return new ResponseEntity<>(orderService.getOrders(userId), HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer orderId) throws Exception {
        OrderResponse orderResponse = orderService.getOrder(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(List<OrderPost> orderPosts) {
        orderService.placeOrder(orderPosts);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/orders/cancel")
    public ResponseEntity<?> cancelOrder(@RequestBody OrderCancelPost orderCancelPost) throws Exception {
        orderService.cancelOrder(orderCancelPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<?> modifyOrderStatus(@RequestBody OrderStatusPut orderStatusPut) throws Exception {
        orderService.modifyOrderStatus(
                orderStatusPut.getOrderId(),
                OrderStatus.valueOf(orderStatusPut.getStatus()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

















