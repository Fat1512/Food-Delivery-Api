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

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getOrderByUser(Principal principal) {
        return new ResponseEntity<>(orderService.getOrders(userServiceImpl.getUserByUsername(principal.getName()).getUserId()), HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer orderId) {
        OrderResponse orderResponse = orderService.getOrder(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(Principal principal, List<OrderPost> orderPosts) {
        orderService.placeOrder(orderPosts, userServiceImpl.getUserByUsername(principal.getName()).getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/orders/cancel")
    public ResponseEntity<?> cancelOrder(Principal principal, @RequestBody OrderCancelPost orderCancelPost) {
        orderService.cancelOrder(orderCancelPost, userServiceImpl.getUserByUsername(principal.getName()).getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<CartResponse> modifyOrderStatus(@RequestBody OrderStatusPut orderStatusPut) {
        orderService.modifyOrderStatus(
                orderStatusPut.getOrderId(),
                OrderStatus.valueOf(orderStatusPut.getStatus()));
        return null;
    }
}

















