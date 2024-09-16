package com.food.phat.service.Impl;

import com.food.phat.dto.order.OrderCancelPost;
import com.food.phat.dto.order.OrderItemPost;
import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.order.OrderResponse;
import com.food.phat.entity.*;
import com.food.phat.mapstruct.OrderCancelMapper;
import com.food.phat.mapstruct.OrderMapper;
import com.food.phat.repository.*;
import com.food.phat.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;
    private final OrderCancelMapper orderCancelMapper;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderMapper orderMapper,
            CartItemRepository cartItemRepository,
            OrderCancelMapper orderCancelMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.cartItemRepository = cartItemRepository;
        this.orderCancelMapper = orderCancelMapper;
    }

    @Override
    @Transactional
    public List<OrderResponse> getOrders(Integer userId) {
        List<Order> orderList = orderRepository.findByUser_UserId(userId);
        return orderList.stream().map(orderMapper::toDto).toList();
    }

    @Override
    @Transactional
    public OrderResponse getOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public void placeOrder(List<OrderPost> orderPosts, Integer userId) {
        orderPosts.forEach(orderPost -> {
            Order order = orderMapper.toEntity(orderPost);

            List<Integer> productIds = orderPost.getOrderItems().stream()
                    .map(OrderItemPost::getProductId).toList();

            cartItemRepository.deleteAllByProductIdAndUserId(productIds, userId);
            orderRepository.save(order);
        });
    }

    @Override
    public void cancelOrder(OrderCancelPost orderCancelPost, Integer userId) {

    }

    @Override
    public void modifyOrderStatus(Integer orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(status);
        orderRepository.save(order);
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





















