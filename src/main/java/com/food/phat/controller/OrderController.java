package com.food.phat.controller;


import com.food.phat.dto.request.OrderRequest;
import com.food.phat.dto.response.CartResponse;
import com.food.phat.dto.response.OrderResponse;
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
    private OrderService orderService;
    private UserService userService;
    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderResponse>> getOrder(Principal principal) {
        return new ResponseEntity<>(orderService.getOrders(userService.getUserByUsername(principal.getName()).getUserId()), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<CartResponse> placeOrder(List<OrderRequest> orderRequests) {
        orderService.placeOrder(orderRequests);
        return null;
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<CartResponse> deleteOrder(@PathVariable Integer orderId) {
        orderService.modifyOrderStatus(orderId, "CANCELLED");
        return null;
    }

    @PatchMapping("/order/{orderId}")
    public ResponseEntity<CartResponse> modifyOrderStatus(
            @PathVariable Integer orderId,
            @RequestParam(name="status", defaultValue = "CONFIRMED") String status) {
        orderService.modifyOrderStatus(orderId, status);
        return null;
    }
}









