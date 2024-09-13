package com.food.phat.controller;


import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.cart.CartResponse;
import com.food.phat.dto.order.OrderResponse;
import com.food.phat.dto.order.OrderStatusPut;
import com.food.phat.entity.OrderStatus;
import com.food.phat.service.CartService;
import com.food.phat.service.Impl.UserService;
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
    private final CartService cartService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, CartService cartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderResponse>> getOrderByUser(Principal principal) {
        return new ResponseEntity<>(orderService.getOrders(userService.getUserByUsername(principal.getName()).getUserId()), HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderResponse>> getOrderById(@PathVariable Integer orderId) {
        return null;
    }

    @PostMapping("/order")
    public ResponseEntity<CartResponse> placeOrder(List<OrderPost> orderPosts) {
        orderService.placeOrder(orderPosts);
        return null;
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<CartResponse> cancelOrder(@PathVariable Integer orderId) {
        orderService.modifyOrderStatus(orderId, OrderStatus.CANCELLED);
        return null;
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<CartResponse> modifyOrderStatus(@RequestBody OrderStatusPut orderStatusPut) {
        orderService.modifyOrderStatus(
                orderStatusPut.getOrderId(),
                OrderStatus.valueOf(orderStatusPut.getStatus()));
        return null;
    }
}









