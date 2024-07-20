package com.food.phat.service.Impl;

import com.food.phat.dto.request.CartItemRequest;
import com.food.phat.dto.request.CartRequest;
import com.food.phat.dto.response.CartItemDetailResponse;
import com.food.phat.dto.response.CartItemResponse;
import com.food.phat.dto.response.CartResponse;
import com.food.phat.entity.Cart;
import com.food.phat.entity.CartItem;
import com.food.phat.entity.Product;
import com.food.phat.entity.Restaurant;
import com.food.phat.repository.CartItemRepository;
import com.food.phat.repository.CartRepository;
import com.food.phat.repository.ModifierOptionRepository;
import com.food.phat.repository.ProductRepository;
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
    private ProductRepository productRepository;
    private ModifierOptionRepository modifierOptionRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository
            , CartItemRepository cartItemRepository
            , ModifierOptionRepository modifierOptionRepository
            , ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.modifierOptionRepository = modifierOptionRepository;
        this.productRepository = productRepository;
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
        formatCartMap.forEach((res, cartItem) -> {
            CartItemResponse cartItemResponse = new CartItemResponse();
            cartItemResponse.setRestaurantId(res.getRestaurantId());
            cartItemResponse.setRestaurantName(res.getName());
            cartItem.forEach(cartItemz -> cartItemResponse.addCartDetailResponse(getCartDetailResponse(cartItemz)));
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
    public void updateCartItem(CartRequest cartRequest) {
        CartItemRequest cartItemRequest = cartRequest.getCartItemRequest();
        CartItem cartItem = cartItemRepository.findById(cartItemRequest.getCartItemId()).orElse(new CartItem());

        cartItem.setQty(cartItemRequest.getQty());
        cartItem.setNote(cartItemRequest.getNote());
        cartItem.setModifierOptions(cartItemRequest
                .getModifierOptionsId().stream()
                .map(id -> modifierOptionRepository.findById(id).get()).collect(Collectors.toCollection(ArrayList::new)));
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void saveCartItem(CartRequest cartRequest) {

        CartItem cartItem = new CartItem();
        CartItemRequest cartItemRequest = new CartItemRequest();
        Cart cart = cartRepository.findById(cartRequest.getCartId()).get();

        cartItem.setQty(cartItemRequest.getQty());
        cartItem.setNote(cartItemRequest.getNote());
        cartItem.setModifierOptions(cartItemRequest
                .getModifierOptionsId().stream()
                .map(id -> modifierOptionRepository.findById(id).get()).collect(Collectors.toCollection(ArrayList::new)));
        cartItem.setProduct(productRepository.findById(cartItemRequest.getProductId()).get());
        cart.addCartItem(cartItem);
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























