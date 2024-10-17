package com.food.phat.controller;

import com.food.phat.dto.MessageResponse;
import com.food.phat.dto.order.OrderCancelPost;
import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.cart.CartResponse;
import com.food.phat.dto.order.OrderResponse;
import com.food.phat.dto.order.OrderStatusPut;
import com.food.phat.entity.OrderStatus;
import com.food.phat.service.Impl.UserServiceImpl;
import com.food.phat.service.OrderService;
import com.food.phat.utils.ApiResponseMessage;
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
    public ResponseEntity<MessageResponse> getOrderByUser(@PathVariable Integer userId) throws Exception {
        List<OrderResponse> orderResponses = orderService.getOrders(userId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(orderResponses)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<MessageResponse> getOrderById(@PathVariable Integer orderId) throws Exception {
        OrderResponse orderResponse = orderService.getOrder(orderId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(orderResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<MessageResponse> placeOrder(List<OrderPost> orderPosts) {
        orderService.placeOrder(orderPosts);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PostMapping("/orders/cancel")
    public ResponseEntity<MessageResponse> cancelOrder(@RequestBody OrderCancelPost orderCancelPost) throws Exception {
        orderService.cancelOrder(orderCancelPost);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_DELETED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<MessageResponse> modifyOrderStatus(@RequestBody OrderStatusPut orderStatusPut) throws Exception {
        orderService.modifyOrderStatus(
                orderStatusPut.getOrderId(),
                OrderStatus.valueOf(orderStatusPut.getStatus()));
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_UPDATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);    }
}

















