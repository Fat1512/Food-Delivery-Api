package com.food.phat.service.Impl;

import com.food.phat.dto.CustomerAddressDTO;
import com.food.phat.dto.request.order.OrderRequest;
import com.food.phat.dto.response.cart.CartItemResponse;
import com.food.phat.dto.response.order.OrderResponse;
import com.food.phat.entity.*;
import com.food.phat.repository.*;
import com.food.phat.service.OrderService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public List<OrderResponse> getOrders(Integer userId) {
        List<Order> orderList = orderRepository.findByCustomerAddress_User_UserId(userId);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        orderList.forEach(order -> {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(order.getOrderId());
            orderResponse.setShippingFee(order.getShippingFee());
            orderResponse.setStatus(order.getOrderStatus());
            CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
            customerAddressDTO.setAddress(order.getCustomerAddress().getAddress());
            customerAddressDTO.setCountry(order.getCustomerAddress().getCountry());
            customerAddressDTO.setCity(order.getCustomerAddress().getCity());
            customerAddressDTO.setPhone(order.getCustomerAddress().getPhone());
            orderResponse.setCustomerAddress(customerAddressDTO);
            orderResponse.getRestaurantInfo().put("restaurantId", order.getRestaurant().getRestaurantId());
            orderResponse.getRestaurantInfo().put("restaurantName", order.getRestaurant().getName());
            order.getOrderItem().forEach(orderItem -> orderResponse.addCartItemResponse(getCartItemResponse(orderItem)));
            orderResponseList.add(orderResponse);
        });
        return orderResponseList;
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
    public void modifyOrderStatus(Integer orderId, String status) {
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.valueOf(status));
    }

    private OrderResponse mapToOrderResponse() {
        return null;
    }


    private static CartItemResponse getCartItemResponse(OrderItem orderItem) {
        Product prodEntity = orderItem.getProduct();

        List<Object[]> modifierObject = new ArrayList<>();
        orderItem.getModifierOptions().forEach(option -> {
            List<Object> objectList = new ArrayList<>();
            objectList.add(option.getModifier().getTitle());
            objectList.add(option);
            modifierObject.add(objectList.toArray());
        });

        CartItemResponse cartItemResponse = new CartItemResponse(
                prodEntity.getProductId(),
                prodEntity.getName(),
                prodEntity.getStatus(),
                prodEntity.getDescription(),
                prodEntity.getPrice(),
                orderItem.getQty(),
                orderItem.getNote(),
                prodEntity.getThumbnail(),
                modifierObject,
                prodEntity.getCategory());
        return cartItemResponse;
    }
}
