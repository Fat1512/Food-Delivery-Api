package com.food.phat.service.Impl;

import com.food.phat.dto.order.OrderCancelPost;
import com.food.phat.dto.order.OrderItemPost;
import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.order.OrderResponse;
import com.food.phat.entity.*;
import com.food.phat.exception.ResourceNotFoundException;
import com.food.phat.exception.UnauthorizedException;
import com.food.phat.mapstruct.order.OrderCancelMapper;
import com.food.phat.mapstruct.order.OrderMapper;
import com.food.phat.repository.*;
import com.food.phat.service.OrderService;
import com.food.phat.utils.AuthenticationUtil;
import com.food.phat.utils.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final RestaurantRepository restaurantRepository;
    private final OrderCancelRepository orderCancelRepository;

    @Override
    @Transactional
    public List<OrderResponse> getOrders(Integer userId) throws Exception {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());

        if(!Objects.equals(user.getUserId(), userId)) throw new UnauthorizedException("User id doesn't match");

        List<Order> orderList = orderRepository.findByUser_UserId(userId);
        return orderList.stream().map(orderMapper::toDto).toList();
    }

    @Override
    @Transactional
    public OrderResponse getOrder(Integer orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if(!isOwnedOrder(order.getUser().getUserId())) throw new ResourceNotFoundException("Order is not yours");
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public void placeOrder(List<OrderPost> orderPosts) {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());

        orderPosts.forEach(orderPost -> {
            Order order = orderMapper.toEntity(orderPost);

            List<Integer> productIds = orderPost.getOrderItems().stream()
                    .map(OrderItemPost::getProductId).toList();

            cartItemRepository.deleteAllByProductIdAndUserId(productIds, user.getUserId());
            orderRepository.save(order);
        });
    }

    @Override
    @Transactional
    public void cancelOrder(OrderCancelPost orderCancelPost) throws Exception {
        Order order =  orderRepository.findById(orderCancelPost.getOrderId())
                .orElseThrow(() -> new Exception("Order not found"));

        if(!isOwnedOrder(order.getUser().getUserId())) throw new ResourceNotFoundException("Order id is not yours");
        order.setOrderStatus(OrderStatus.CANCELLED);

        OrderCancel orderCancel = new OrderCancel();
        orderCancel.setReason(orderCancelPost.getReason());
        orderCancel.setOrder(order);
        orderCancel.setOrderId(orderCancelPost.getOrderId());
        orderCancelRepository.save(orderCancel);
    }

    @Override
    @Transactional
    public void modifyOrderStatus(Integer orderId, OrderStatus status) throws Exception {
        if(!this.isRestaurant()) {
            throw new UnauthorizedException("You're not restaurant");
        }

        Authentication authentication = AuthenticationUtil.getAuthentication();

        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());
        Restaurant restaurant = restaurantRepository.findByUserId(user.getUserId());

        Order order = orderRepository.findByIdAndRestaurantId(orderId, restaurant.getRestaurantId());
        if(order == null) throw new ResourceNotFoundException("Order not found");

        order.setOrderStatus(status);
        orderRepository.save(order);
    }

    private boolean isRestaurant() {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals(Role.RESTAURANT.name()));
    }

    private boolean isOwnedOrder(Integer userId) {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());
        return user.getUserId().equals(userId);
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





















