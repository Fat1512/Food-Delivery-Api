package com.food.phat.controller;


import com.food.phat.dto.cart.CartItemPut;
import com.food.phat.dto.cart.CartItemRequest;
import com.food.phat.dto.cart.CartResponse;
import com.food.phat.entity.CartItem;
import com.food.phat.repository.CartItemRepository;
import com.food.phat.service.CartService;
import com.food.phat.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartController(CartService cartService, UserService userService, CartItemRepository cartItemRepository) {
        this.cartService = cartService;
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping("/cart")
    public ResponseEntity<CartResponse> getCartByUserId(Principal principal) {
        return new ResponseEntity<>(cartService.getCart(userService.getUserByUsername(principal.getName()).getUserId()),
                HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<String> deleteCartItem(Principal principal, @RequestBody Map<String, Integer[]> cartDetailIdList) {
        cartService.deleteCartItem(
                Arrays.stream(cartDetailIdList.get("idList")).toList(),
                userService.getUserByUsername(principal.getName()).getUserId());
        return null;
    }

    @PostMapping("/cart")
    public ResponseEntity<String> saveCartItem(Principal principal, @RequestBody CartItemRequest cartItemRequest) {
        cartService.saveCartItem(cartItemRequest, userService.getUserByUsername(principal.getName()).getUserId());
        return null;
    }

    @PutMapping("/cart")
    public ResponseEntity<String> updateCartItem(Principal principal, @RequestBody CartItemPut cartItemPut) {

    }
}
