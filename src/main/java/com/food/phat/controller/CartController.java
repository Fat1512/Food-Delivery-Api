package com.food.phat.controller;


import com.food.phat.entity.Cart;
import com.food.phat.service.CartService;
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

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> getCartItems(Principal principal) {
        return new ResponseEntity<>(cartService.getCartItem(principal.getName()), HttpStatus.OK);
    }

}
/**
 *
 *  items : [
 *      {
 *          name: "asdasd"
 *          description: "asdas"
 *          price: "asdads"
 *          qty: "123"
 *
 *      }
 *  ]
 *
 *
 */