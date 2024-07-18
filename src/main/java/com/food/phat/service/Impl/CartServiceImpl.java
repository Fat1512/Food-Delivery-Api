package com.food.phat.service.Impl;

import com.food.phat.dto.CartDetailResponse;
import com.food.phat.dto.CartItemResponse;
import com.food.phat.dto.CartResponse;
import com.food.phat.entity.Cart;
import com.food.phat.entity.CartDetail;
import com.food.phat.entity.Product;
import com.food.phat.entity.Restaurant;
import com.food.phat.repository.CartRepository;
import com.food.phat.repository.RestaurantRepo;
import com.food.phat.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private RestaurantRepo restaurantRepo;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository, RestaurantRepo restaurantRepo) {
        this.cartRepository = cartRepository;
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    @Transactional
    public CartResponse getCart(Integer customerId) {
        List<Restaurant> restaurantList = restaurantRepo.getRestaurantByCustomerId(customerId);
        Cart cart = cartRepository.getCartByUser_UserId(customerId);

        CartResponse cartResponse = new CartResponse();

        restaurantList.forEach(res -> {
            CartItemResponse cartItemResponse = new CartItemResponse();
            cartItemResponse.setRestaurantId(res.getRestaurantId());
            cartItemResponse.setRestaurantName(res.getName());

            cart.getCartDetail().forEach(cartDetail -> {
                if(Objects.equals(cartDetail.getProduct().getRestaurant().getRestaurantId(), res.getRestaurantId())) {
                    CartDetailResponse cartDetailResponse = getCartDetailResponse(cartDetail);
                    cartItemResponse.addCartDetailResponse(cartDetailResponse);
                }
            });
            cartResponse.addCartItem(cartItemResponse);
        });
        return cartResponse;
    }

    private static CartDetailResponse getCartDetailResponse(CartDetail cartDetail) {
        Product prodEntity = cartDetail.getProduct();
        CartDetailResponse cartDetailResponse = new CartDetailResponse(
                prodEntity.getProductId(),
                prodEntity.getName(),
                prodEntity.getStatus(),
                prodEntity.getDescription(),
                prodEntity.getPrice(),
                cartDetail.getQty(),
                cartDetail.getNote(),
                prodEntity.getThumbnail(),
                cartDetail.getModifierOptions(),
                prodEntity.getCategory());
        return cartDetailResponse;
    }
}


/**
 *     private Integer productId;
 *     private String name;
 *     private Boolean status;
 *     private String description;
 *     private float price;
 *     private String thumbnail;
 *     List<Object[]> modifiers;
 *     private Category category;
 */








