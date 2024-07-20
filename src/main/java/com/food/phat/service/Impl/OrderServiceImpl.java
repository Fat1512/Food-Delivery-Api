package com.food.phat.service.Impl;

import com.food.phat.dto.request.OrderRequest;
import com.food.phat.entity.Order;
import com.food.phat.entity.OrderItem;
import com.food.phat.entity.OrderStatus;
import com.food.phat.repository.*;
import com.food.phat.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private RestaurantRepository restaurantRepository;
    private CustomerAddressRepository customerAddressRepository;
    private ModifierOptionRepository modifierOptionRepository;

    public OrderServiceImpl(OrderRepository orderRepository
            , ProductRepository productRepository
            , RestaurantRepository restaurantRepository
            , CustomerAddressRepository customerAddressRepository
            , ModifierOptionRepository modifierOptionRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
        this.customerAddressRepository = customerAddressRepository;
        this.modifierOptionRepository = modifierOptionRepository;
    }

    @Override
    public void placeOrder(List<OrderRequest> orderRequests) {
        orderRequests.forEach(orderRequest -> {
            Order order = new Order();
            order.setOrderStatus(OrderStatus.PENDING);
            orderRequest.getOrderItems().forEach(orderItemRequest -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setQty(orderItemRequest.getQty());
                orderItem.setPrice(orderItemRequest.getPrice());
                orderItem.setNote(orderItemRequest.getNote());
                orderItem.setNote(orderItemRequest.getNote());
                orderItem.setProduct(productRepository.findById(orderItemRequest.getProductId()).get());
                orderItem.setModifierOptions(orderItemRequest
                        .getModifierOptionsId().stream()
                        .map(id -> modifierOptionRepository.findById(id).get()).collect(Collectors.toCollection(ArrayList::new)));
                order.addOrderItem(orderItem);
            });

            order.setRestaurant(restaurantRepository.findById(orderRequest.getRestaurantId()).get());
            order.setCustomerAddress(customerAddressRepository.findById(orderRequest.getCustomerAddressId()).get());
            orderRepository.save(order);
        });
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}
