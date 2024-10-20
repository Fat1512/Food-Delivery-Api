package com.food.phat.service.Impl;

import com.food.phat.dto.cart.CartItemPut;
import com.food.phat.dto.cart.CartItemPost;
import com.food.phat.dto.cart.CartResponse;
import com.food.phat.entity.Cart;
import com.food.phat.entity.CartItem;
import com.food.phat.entity.User;
import com.food.phat.exception.ResourceNotFoundException;
import com.food.phat.exception.UnauthorizedException;
import com.food.phat.mapstruct.cart.CartItemMapper;
import com.food.phat.mapstruct.cart.CartMapper;
import com.food.phat.repository.CartItemRepository;
import com.food.phat.repository.CartRepository;
import com.food.phat.repository.UserRepository;
import com.food.phat.service.CartService;
import com.food.phat.utils.AuthenticationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.lang.reflect.UndeclaredThrowableException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    @Transactional
    public CartResponse getCart(Integer userId) throws Exception {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((UserDetails)authentication.getPrincipal()).getUsername());

        if(!user.getUserId().equals(userId)) throw new UnauthorizedException("User id doesn't match");

        Cart cart = cartRepository.findByUser_UserId(user.getUserId());
        return cartMapper.toDto(cart);
    }

    @Override
    @Transactional
    public void saveCartItem(CartItemPost cartItemPost) {

        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());

        if(user == null) throw new UnauthorizedException("User doesn't exist");

        Cart cart = cartRepository.findByUser_UserId(user.getUserId());
        CartItem cartItem = cartItemMapper.toEntity(cartItemPost);
        cart.addCartItem(cartItem);
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void updateCartItem(CartItemPut cartItemPut) throws Exception {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());

        CartItem cartItem = cartItemRepository.findByIdAndUserId(cartItemPut.getCartItemId(), user.getUserId());
        if(cartItem == null) throw new ResourceNotFoundException("Cart item doesn't exist");
        cartItemMapper.updateEntity(cartItemPut, cartItem);
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void deleteCartItem(List<Integer> cartItemIds) {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());

        List<CartItem> cartItems = cartItemRepository.findAllByIdAndUserId(cartItemIds, user.getUserId());
        cartItemRepository.deleteAll(cartItems);
//        if(cartItemIds.size() != cartItems.size()) throw new Error("desired delete cart item doesn't match with current user id");
    }
}

































//    private CartItem mapCartItemRequestToCartItem(CartRequest cartRequest) {
//        Cart cart = cartRepository.findById(cartRequest.getCartId()).get();
//
//        CartItem cartItem = cartItemRepository.findById(cartRequest.getCartItemId()).orElse(new CartItem());
//        cartItem.setQty(cartRequest.getQty());
//        cartItem.setNote(cartRequest.getNote());
//        cartItem.setModifiers(cartRequest
//                .getModifierOptionsId().stream()
//                .map(id -> modifierRepository.findById(id).get()).collect(Collectors.toCollection(ArrayList::new)));
//        cartItem.setCartItemId(cartRequest.getCartItemId());
//        cartItem.setCart(cart);
//
//        if(cartItem.getCartItemId() == null) {
//            cartItem.setProduct(productRepository.findById(cartRequest.getProductId()).get());
//        }
//
//        return cartItem;
//    }
//        private CartResponse mapCartToCartResponse(Cart cart) {
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
//            cartItem.forEach(cartItemz -> cartDetailResponse.addOrderItemResponse(mapCartItemToCartItemResponse(cartItemz)));
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