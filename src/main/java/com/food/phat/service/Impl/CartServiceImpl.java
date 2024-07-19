package com.food.phat.service.Impl;

import com.food.phat.dto.request.CartDetailRequest;
import com.food.phat.dto.response.CartDetailResponse;
import com.food.phat.dto.response.CartItemResponse;
import com.food.phat.dto.response.CartResponse;
import com.food.phat.entity.*;
import com.food.phat.repository.CartDetailRepository;
import com.food.phat.repository.CartRepository;
import com.food.phat.repository.ModifierOptionRepository;
import com.food.phat.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private CartDetailRepository cartDetailRepository;
    private ModifierOptionRepository modifierOptionRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartDetailRepository cartDetailRepository, ModifierOptionRepository modifierOptionRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.modifierOptionRepository = modifierOptionRepository;
    }

    @Override
    @Transactional
    public CartResponse getCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).get();

        Map<Restaurant, List<CartDetail>> formatCartMap = new HashMap<>();
        cart.getCartDetail().forEach(cartDetail -> {
            formatCartMap.computeIfAbsent(cartDetail.getProduct().getRestaurant(), k -> new ArrayList<>());
            formatCartMap.get(cartDetail.getProduct().getRestaurant()).add(cartDetail);
        });

        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartId(cart.getCartId());
        formatCartMap.forEach((res, cardDT) -> {
            CartItemResponse cartItemResponse = new CartItemResponse();
            cartItemResponse.setRestaurantId(res.getRestaurantId());
            cartItemResponse.setRestaurantName(res.getName());
            cardDT.forEach(cartDetail -> cartItemResponse.addCartDetailResponse(getCartDetailResponse(cartDetail)));
            cartResponse.addCartItem(cartItemResponse);
        });

        return cartResponse;
    }

    @Override
    @Transactional
    public void deleteCartDetail(List<Integer> cartDetailId) {
        cartDetailRepository.deleteAllById(cartDetailId::listIterator);
    }

    @Override
    @Transactional
    public void updateCartDetail(CartDetailRequest cartDetailRequest) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailRequest.getCartDetailId()).get();
        List<ModifierOption> modifierOptions = cartDetailRequest
                .getModifierOptionsId().stream()
                .map(id -> modifierOptionRepository.findById(id).get()).collect(Collectors.toCollection(ArrayList::new));

                cartDetail.setQty(cartDetailRequest.getQty());
        cartDetail.setNote(cartDetailRequest.getNote());
        cartDetail.setModifierOptions(modifierOptions);
        cartDetailRepository.save(cartDetail);
    }

    private static CartDetailResponse getCartDetailResponse(CartDetail cartDetail) {
        Product prodEntity = cartDetail.getProduct();

        List<Object[]> modifierObject = new ArrayList<>();
        cartDetail.getModifierOptions().forEach(option -> {
            List<Object> objectList = new ArrayList<>();
            objectList.add(option.getModifier().getTitle());
            objectList.add(option);
            modifierObject.add(objectList.toArray());
        });

        CartDetailResponse cartDetailResponse = new CartDetailResponse(
                prodEntity.getProductId(),
                prodEntity.getName(),
                prodEntity.getStatus(),
                prodEntity.getDescription(),
                prodEntity.getPrice(),
                cartDetail.getQty(),
                cartDetail.getNote(),
                prodEntity.getThumbnail(),
                modifierObject,
                prodEntity.getCategory());
        return cartDetailResponse;
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
//            cart.getCartDetail().forEach(cartDetail -> {
//                if(Objects.equals(cartDetail.getProduct().getRestaurant().getRestaurantId(), res.getRestaurantId())) {
//                    CartDetailResponse cartDetailResponse = getCartDetailResponse(cartDetail);
//                    cartItemResponse.addCartDetailResponse(cartDetailResponse);
//                }
//            });
//
//            cartResponse.addCartItem(cartItemResponse);
//        });
//        return cartResponse;
//    }
}























