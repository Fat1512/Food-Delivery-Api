package com.food.phat.service.Impl;

import com.food.phat.dto.request.CartDetailRequest;
import com.food.phat.dto.response.CartItemDetailResponse;
import com.food.phat.dto.response.CartItemResponse;
import com.food.phat.dto.response.CartResponse;
import com.food.phat.entity.*;
import com.food.phat.repository.CartItemRepository;
import com.food.phat.repository.CartRepository;
import com.food.phat.repository.ModifierOptionRepository;
import com.food.phat.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ModifierOptionRepository modifierOptionRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ModifierOptionRepository modifierOptionRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.modifierOptionRepository = modifierOptionRepository;
    }

    @Override
    @Transactional
    public CartResponse getCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).get();

        Map<Restaurant, List<CartItem>> formatCartMap = new HashMap<>();
        cart.getCartItem().forEach(cartItem -> {
            formatCartMap.computeIfAbsent(cartItem.getProduct().getRestaurant(), k -> new ArrayList<>());
            formatCartMap.get(cartItem.getProduct().getRestaurant()).add(cartItem);
        });

        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartId(cart.getCartId());
        formatCartMap.forEach((res, cardDT) -> {
            CartItemResponse cartItemResponse = new CartItemResponse();
            cartItemResponse.setRestaurantId(res.getRestaurantId());
            cartItemResponse.setRestaurantName(res.getName());
            cardDT.forEach(cartItem -> cartItemResponse.addCartDetailResponse(getCartDetailResponse(cartItem)));
            cartResponse.addCartItem(cartItemResponse);
        });

        return cartResponse;
    }

    @Override
    @Transactional
    public void deleteCartItem(List<Integer> cartDetailId) {
        cartItemRepository.deleteAllById(cartDetailId::listIterator);
    }

    @Override
    @Transactional
    public void updateCartItem(CartDetailRequest cartDetailRequest) {
        CartItem cartItem = cartItemRepository.findById(cartDetailRequest.getCartDetailId()).get();
        List<ModifierOption> modifierOptions = cartDetailRequest
                .getModifierOptionsId().stream()
                .map(id -> modifierOptionRepository.findById(id).get()).collect(Collectors.toCollection(ArrayList::new));

        cartItem.setQty(cartDetailRequest.getQty());
        cartItem.setNote(cartDetailRequest.getNote());
        cartItem.setModifierOptions(modifierOptions);
        cartItemRepository.save(cartItem);
    }

    private static CartItemDetailResponse getCartDetailResponse(CartItem cartItem) {
        Product prodEntity = cartItem.getProduct();

        List<Object[]> modifierObject = new ArrayList<>();
        cartItem.getModifierOptions().forEach(option -> {
            List<Object> objectList = new ArrayList<>();
            objectList.add(option.getModifier().getTitle());
            objectList.add(option);
            modifierObject.add(objectList.toArray());
        });

        CartItemDetailResponse cartItemDetailResponse = new CartItemDetailResponse(
                prodEntity.getProductId(),
                prodEntity.getName(),
                prodEntity.getStatus(),
                prodEntity.getDescription(),
                prodEntity.getPrice(),
                cartItem.getQty(),
                cartItem.getNote(),
                prodEntity.getThumbnail(),
                modifierObject,
                prodEntity.getCategory());
        return cartItemDetailResponse;
    }

//    @Override
//    @Transactional
//    public CartResponse getCart(Integer cartId) {
//        Cart cart = cartRepository.findById(cartId).get();
//        List<Restaurant> restaurantList = restaurantRepo.getRestaurantByCustomerId(cart.getUser().getUserId());
//        CartResponse cartResponse = new CartResponse();
//
//        restaurantList.forEach(res -> {
//            CartItemResponse cartItemResponse = new CartItemResponse();
//            cartItemResponse.setRestaurantId(res.getRestaurantId());
//            cartItemResponse.setRestaurantName(res.getName());
//
//            cart.getCartItem().forEach(cartDetail -> {
//                if(Objects.equals(cartDetail.getProduct().getRestaurant().getRestaurantId(), res.getRestaurantId())) {
//                    CartItemDetailResponse cartDetailResponse = getCartDetailResponse(cartDetail);
//                    cartItemResponse.addCartDetailResponse(cartDetailResponse);
//                }
//            });
//
//            cartResponse.addCartItem(cartItemResponse);
//        });
//        return cartResponse;
//    }
}























