package com.food.phat.controller;


import com.food.phat.dto.cart.CartItemPut;
import com.food.phat.dto.cart.CartItemPost;
import com.food.phat.dto.cart.CartResponse;
import com.food.phat.service.CartService;
import com.food.phat.service.Impl.UserServiceImpl;
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
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public CartController(CartService cartService, UserServiceImpl userServiceImpl) {
        this.cartService = cartService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/carts")
    public ResponseEntity<CartResponse> getCartByUserId(Principal principal) {
        return new ResponseEntity<>(cartService.getCart(userServiceImpl.getUserByUsername(principal.getName()).getUserId()),
                HttpStatus.OK);
    }

    @DeleteMapping("/carts")
    public ResponseEntity<String> deleteCartItem(Principal principal, @RequestBody Map<String, Integer[]> cartDetailIdList) {
        cartService.deleteCartItem(
                Arrays.stream(cartDetailIdList.get("idList")).toList(),
                userServiceImpl.getUserByUsername(principal.getName()).getUserId());
        return null;
    }

    @PostMapping("/carts")
    public ResponseEntity<String> saveCartItem(Principal principal, @RequestBody CartItemPost cartItemPost) {
        cartService.saveCartItem(cartItemPost, userServiceImpl.getUserByUsername(principal.getName()).getUserId());
        return null;
    }

    @PutMapping("/carts")
    public ResponseEntity<String> updateCartItem(Principal principal, @RequestBody CartItemPut cartItemPut) {
        cartService.updateCartItem(cartItemPut, userServiceImpl.getUserByUsername(principal.getName()).getUserId());
        return null;
    }
}
