package com.food.phat.mapstruct;


import com.food.phat.dto.customer.CustomerAddressDTO;
import com.food.phat.dto.order.OrderItemResponse;
import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.order.OrderResponse;
import com.food.phat.dto.restaurant.RestaurantCheckoutResponse;
import com.food.phat.entity.Order;
import com.food.phat.entity.OrderItem;
import com.food.phat.repository.CustomerAddressRepository;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderDecorator.class)
public interface OrderMapper {
    OrderResponse toDto(Order order);
    Order toEntity(OrderPost orderPost);
}

@Mapper
abstract class OrderDecorator implements OrderMapper {

    @Qualifier("delegate")
    @Autowired
    private OrderMapper delegate;
    @Autowired
    private RestaurantMapper restaurantMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private CustomerAddressMapper customerAddressMapper;
    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Override
    public OrderResponse toDto(Order order) {
        OrderResponse orderResponse = delegate.toDto(order);
        CustomerAddressDTO customerAddressDTO = customerAddressMapper.toDto(order.getCustomerAddress());
        RestaurantCheckoutResponse restaurantCheckoutResponse = restaurantMapper.toRestaurantCheckoutResponse(order.getRestaurant());
        List<OrderItemResponse> orderItemResponse = order.getOrderItems().stream().map(orderItem -> orderItemMapper.toDto(orderItem)).collect(Collectors.toCollection(ArrayList::new));

        orderResponse.setCustomerAddress(customerAddressDTO);
        orderResponse.setRestaurant(restaurantCheckoutResponse);
        orderResponse.setOrderItems(orderItemResponse);
        return orderResponse;
    }

    @Override
    public Order toEntity(OrderPost orderPost) {
        Order order = delegate.toEntity(orderPost);
        customerAddressRepository.findById(orderPost.getCustomerAddressId());
        List<OrderItem> orderItems = orderPost.getOrderItems().stream().map(orderItem -> orderItemMapper.toEntity(orderItem)).collect(Collectors.toCollection(ArrayList::new));
        order.addOrderItem(orderItems);
        return order;
    }
}


















