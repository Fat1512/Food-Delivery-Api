package com.food.phat.controller;


import com.food.phat.dto.cart.CartItemPut;
import com.food.phat.dto.cart.CartItemPost;
import com.food.phat.dto.cart.CartResponse;
import com.food.phat.service.CartService;
import com.food.phat.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/users/{userId}/carts")
    public ResponseEntity<CartResponse> getCartByUserId(@PathVariable Integer userId) throws Exception {
        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.OK);
    }

    @DeleteMapping("/carts")
    public ResponseEntity<String> deleteCartItems( @RequestBody Map<String, Integer[]> cartDetailIdList) {
        cartService.deleteCartItem(Arrays.stream(cartDetailIdList.get("idList")).toList());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/carts")
    public ResponseEntity<String> saveCartItem( @RequestBody CartItemPost cartItemPost) {
        cartService.saveCartItem(cartItemPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/carts/{cartId}/cart-items/{cartItemId}")
    public ResponseEntity<String> updateCartItem(@RequestBody CartItemPut cartItemPut) throws Exception {
        cartService.updateCartItem(cartItemPut);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
