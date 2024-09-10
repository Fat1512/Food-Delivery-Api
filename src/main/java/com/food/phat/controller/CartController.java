package com.food.phat.controller;


import com.food.phat.dto.request.CartRequest;
import com.food.phat.dto.response.CartResponse;
import com.food.phat.entity.Cart;
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
    CartService cartService;
    UserService userService;
    @Autowired
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public ResponseEntity<CartResponse> getCartByUserId(Principal principal) {
        return new ResponseEntity<>(cartService.getCart(userService.getUserByUsername(principal.getName()).getUserId()),
                HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<String> deleteCartItem(@RequestBody Map<String, Integer[]> cartDetailIdList) {
        cartService.deleteCartItem(Arrays.stream(cartDetailIdList.get("idList")).toList());
        return null;
    }

    @PostMapping("/cart")
    public ResponseEntity<String> saveOrUpdateCart(@RequestBody CartRequest cartRequest) {
        cartService.saveOrUpdateCartItem(cartRequest);
        return null;
    }
}
