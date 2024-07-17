package com.food.phat.controller;


import com.food.phat.dto.CartResponse;
import com.food.phat.service.CartService;
import com.food.phat.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
    public ResponseEntity<CartResponse> getCart(Principal principal) {
        return new ResponseEntity<>(cartService.getCart(userService.getUserByUsername(principal.getName()).getUserId()), HttpStatus.OK);
    }
}
