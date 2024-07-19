package com.food.phat.controller;


import com.food.phat.dto.request.CartDetailRequest;
import com.food.phat.dto.response.CartResponse;
import com.food.phat.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CartController {
    CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable(name="cartId") Integer cartId) {
        return new ResponseEntity<>(cartService.getCart(cartId), HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<String> deleteCartDetail(@RequestBody Map<String, Integer[]> cartDetailIdList) {
        cartService.deleteCartDetail(Arrays.stream(cartDetailIdList.get("idList")).toList());
        return null;
    }

    @PatchMapping("/cart")
    public ResponseEntity<String> updateCart(@RequestBody CartDetailRequest cartDetailRequest) {
        cartService.updateCartDetail(cartDetailRequest);
        return null;
    }
}
