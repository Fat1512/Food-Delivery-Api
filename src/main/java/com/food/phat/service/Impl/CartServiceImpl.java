package com.food.phat.service.Impl;

import com.food.phat.dto.request.cart.CartRequest;
import com.food.phat.dto.response.cart.CartResponse;
import com.food.phat.entity.Cart;
import com.food.phat.entity.CartItem;
import com.food.phat.repository.CartItemRepository;
import com.food.phat.repository.CartRepository;
import com.food.phat.repository.ModifierOptionRepository;
import com.food.phat.repository.ProductRepository;
import com.food.phat.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public CartResponse getCart(Integer userId) {
        Cart cart = cartRepository.findByUser_UserId(userId);
//        return mapCartToCartResponse(cart);
        return null;
    }

    @Override
    @Transactional
    public void saveOrUpdateCartItem(CartRequest cartRequest) {
        cartItemRepository.save(mapCartItemRequestToCartItem(cartRequest));
    }

    @Override
    @Transactional
    public void deleteCartItem(List<Integer> cartDetailId) {
        cartItemRepository.deleteAllById(cartDetailId::listIterator);
    }

    private CartItem mapCartItemRequestToCartItem(CartRequest cartRequest) {
        Cart cart = cartRepository.findById(cartRequest.getCartId()).get();

        CartItem cartItem = cartItemRepository.findById(cartRequest.getCartItemId()).orElse(new CartItem());
        cartItem.setQty(cartRequest.getQty());
        cartItem.setNote(cartRequest.getNote());
        cartItem.setModifiers(cartRequest
                .getModifierOptionsId().stream()
                .map(id -> modifierOptionRepository.findById(id).get()).collect(Collectors.toCollection(ArrayList::new)));
        cartItem.setCartItemId(cartRequest.getCartItemId());
        cartItem.setCart(cart);

        if(cartItem.getCartItemId() == null) {
            cartItem.setProduct(productRepository.findById(cartRequest.getProductId()).get());
        }

        return cartItem;
    }
//
//    private CartResponse mapCartToCartResponse(Cart cart) {
//        CartResponse cartResponse = new CartResponse();
//        cartResponse.setCartId(cart.getCartId());
//
//        Map<Restaurant, List<CartItem>> formatCartMap = new HashMap<>();
//        cart.getCartItem().forEach(cartItem -> {
//            formatCartMap.computeIfAbsent(cartItem.getProduct().getRestaurant(), k -> new ArrayList<>());
//            formatCartMap.get(cartItem.getProduct().getRestaurant()).add(cartItem);
//        });
//
//        formatCartMap.forEach((res, cartItem) -> {
//            CartDetailResponse cartDetailResponse = new CartDetailResponse();
//            cartDetailResponse.getRestaurantInfo().put("restaurantId", res.getRestaurantId());
//            cartDetailResponse.getRestaurantInfo().put("restaurantName", res.getName());
//            cartItem.forEach(cartItemz -> cartDetailResponse.addCartItemResponse(mapCartItemToCartItemResponse(cartItemz)));
//
//            cartResponse.addCartItem(cartDetailResponse);
//        });
//        return cartResponse;
//    }
//
//    private static CartItemResponse mapCartItemToCartItemResponse(CartItem cartItem) {
//        Product prodEntity = cartItem.getProduct();
//
//        List<Object[]> modifierObject = new ArrayList<>();
//
//        cartItem.getModifiers().forEach(option -> {
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
//                cartItem.getQty(),
//                cartItem.getNote(),
//                prodEntity.getThumbnail(),
//                modifierObject,
//                prodEntity.getCategory());
//        return cartItemResponse;
//    }

//    @Override
//    @Transactional
//    public CartResponse getCart(Integer cartId) {
//        Cart cart = cartRepository.findById(cartId).get();
//        List<Restaurant> restaurantList = restaurantRepo.getRestaurantByCustomerId(cart.getUser().getUserId());
//        CartResponse cartResponse = new CartResponse();
//
//        restaurantList.forEach(res -> {
//            CartDetailResponse cartItemResponse = new CartDetailResponse();
//            cartItemResponse.setRestaurantId(res.getRestaurantId());
//            cartItemResponse.setRestaurantName(res.getName());
//
//            cart.getCartItem().forEach(cartDetail -> {
//                if(Objects.equals(cartDetail.getProduct().getRestaurant().getRestaurantId(), res.getRestaurantId())) {
//                    CartItemResponse cartDetailResponse = getCartDetailResponse(cartDetail);
//                    cartItemResponse.addCartDetailResponse(cartDetailResponse);
//                }
//            });
//
//            cartResponse.addCartItem(cartItemResponse);
//        });
//        return cartResponse;
//    }
}
































