package com.food.phat.controller;


import com.food.phat.dto.MessageResponse;
import com.food.phat.dto.cart.CartItemPut;
import com.food.phat.dto.cart.CartItemPost;
import com.food.phat.dto.cart.CartResponse;
import com.food.phat.service.CartService;
import com.food.phat.service.Impl.UserServiceImpl;
import com.food.phat.utils.ApiResponseMessage;
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
    public ResponseEntity<MessageResponse> getCartByUserId(@PathVariable Integer userId) throws Exception {
        CartResponse cartResponse = cartService.getCart(userId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(cartResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/carts")
    public ResponseEntity<MessageResponse> deleteCartItems( @RequestBody Map<String, Integer[]> cartDetailIdList) {
        cartService.deleteCartItem(Arrays.stream(cartDetailIdList.get("idList")).toList());
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_DELETED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/carts")
    public ResponseEntity<MessageResponse> saveCartItem( @RequestBody CartItemPost cartItemPost) {
        cartService.saveCartItem(cartItemPost);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PutMapping("/carts/{cartId}/cart-items/{cartItemId}")
    public ResponseEntity<MessageResponse> updateCartItem(@RequestBody CartItemPut cartItemPut) throws Exception {
        cartService.updateCartItem(cartItemPut);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_UPDATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
