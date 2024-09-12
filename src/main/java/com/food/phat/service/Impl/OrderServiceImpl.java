package com.food.phat.service.Impl;

import com.food.phat.dto.order.OrderRequest;
import com.food.phat.dto.order.OrderResponse;
import com.food.phat.entity.*;
import com.food.phat.repository.*;
import com.food.phat.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;


    public OrderServiceImpl(OrderRepository orderRepository
            , ProductRepository productRepository
            , RestaurantRepository restaurantRepository
            , CustomerAddressRepository customerAddressRepository
            , ModifierRepository modifierRepository) {
        this.orderRepository = orderRepository;
//        this.productRepository = productRepository;
//        this.restaurantRepository = restaurantRepository;
//        this.customerAddressRepository = customerAddressRepository;
//        this.modifierRepository = modifierRepository;
    }

    @Override
    @Transactional
    public List<OrderResponse> getOrders(Integer userId) {
        List<Order> orderList = orderRepository.findByCustomerAddress_User_UserId(userId);
        List<OrderResponse> orderResponseList = new ArrayList<>();

        return orderResponseList;
    }

    @Override
    public OrderResponse getOrder(Integer orderId) {
        orderRepository.findById(orderId);
        return null;
    }

    @Override
    public void placeOrder(List<OrderRequest> orderRequests) {

    }

    @Override
    public void modifyOrderStatus(Integer orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(status);
    }
}




















//    private Order mapOrderRequestToOrder(OrderRequest orderRequest) {
//        Order order = new Order();
//        order.setOrderStatus(OrderStatus.PENDING);
//        orderRequest.getOrderItems().forEach(orderItemRequest -> {
//            OrderItem orderItem = mapOrderItemRequestToOrderItem(orderItemRequest);
//            order.addOrderItem(orderItem);
//        });
//
//        order.setRestaurant(restaurantRepository.findById(orderRequest.getRestaurantId()).get());
//        order.setCustomerAddress(customerAddressRepository.findById(orderRequest.getCustomerAddressId()).get());
//        return order;
//    }

//    private OrderItem mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest) {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setQty(orderItemRequest.getQty());
//        orderItem.setPrice(orderItemRequest.getPrice());
//        orderItem.setNote(orderItemRequest.getNote());
//        orderItem.setNote(orderItemRequest.getNote());
//        orderItem.setProduct(productRepository.findById(orderItemRequest.getProductId()).get());
//        orderItem.setModifiers(orderItemRequest
//                .getModifierOptionsId().stream()
//                .map(id -> modifierRepository.findById(id).get()).collect(Collectors.toCollection(ArrayList::new)));
//        return orderItem;
//    }
//
//    private OrderResponse mapOrderToOrderResponse(Order order) {
//        OrderResponse orderResponse = new OrderResponse();
//        orderResponse.setOrderId(order.getOrderId());
//        orderResponse.setShippingFee(order.getShippingFee());
//        orderResponse.setStatus(order.getOrderStatus());
//        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
//        customerAddressDTO.setAddress(order.getCustomerAddress().getAddress());
//        customerAddressDTO.setCountry(order.getCustomerAddress().getCountry());
//        customerAddressDTO.setCity(order.getCustomerAddress().getCity());
//        customerAddressDTO.setPhone(order.getCustomerAddress().getPhone());
//        orderResponse.setCustomerAddress(customerAddressDTO);
//        orderResponse.getRestaurantInfo().put("restaurantId", order.getRestaurant().getRestaurantId());
//        orderResponse.getRestaurantInfo().put("restaurantName", order.getRestaurant().getName());
////        order.getOrderItem().forEach(orderItem -> orderResponse.addOrderItemResponse(mapCartItemToCartItemResponse(orderItem)));
////        return orderResponse;
//        return null;
//    }

//    private static CartItemResponse mapCartItemToCartItemResponse(OrderItem orderItem) {
//        Product prodEntity = orderItem.getProduct();
//
//        List<Object[]> modifierObject = new ArrayList<>();
//        orderItem.getModifiers().forEach(option -> {
//            List<Object> objectList = new ArrayList<>();
//            objectList.add(option.getModifierGroup().getTitle());
//            objectList.add(option);
//            modifierObject.add(objectList.toArray());
//        });
//
//        CartItemResponse cartItemResponse = new CartItemResponse(
//                prodEntity.getProductId(),
//                prodEntity.getName(),
//                prodEntity.getStatus(),
//                prodEntity.getDescription(),
//                prodEntity.getPrice(),
//                orderItem.getQty(),
//                orderItem.getNote(),
//                prodEntity.getThumbnail(),
//                modifierObject,
//                prodEntity.getCategory());
//        return cartItemResponse;
//    }
/**
 *
 *
 *
 *
 * skuList = skurepo.findByProductId(prodId)
 *  Map<int, boolean> queryOptionValue; co thi gan = true
 *  Sku skutemp = null;
 *
 * skuList.forEach(sku -> {
 *
 *      List optionValue = sku.getOptionValue();
 *      int cnt = 0;
 *      optionValue.forEach(optionval -> {
 *          if(queryOptionValue[optionval.getId()] == true) cnt++;
 *      }
 *      if(cnt == queryOptionValue.size()) {
 *          skutemp = sku; break;
 *      }
 *      cnt = 0;
 * })
 *
 *
 *
 *{
 *     optionValueId: [1, 2, 3, 4]
 *
 *}
 *
 *
 *
 * Ao levent
 *  const skuList = {
 *      "1": [1, 2],
 *       "2": [5, 2],
 *       "3": [1, 6],
 *       "4": [5, 6],
 *  }
 *
 *
 *
 *  Mau sac : 1: do , 5: vang
 *  Size    : 2: lon , 6: nho
 *
 *  state: color: 1, size: 2
 *
 * skulist.forEach(sku -> {
 *
 *
 *
 * })
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */





















