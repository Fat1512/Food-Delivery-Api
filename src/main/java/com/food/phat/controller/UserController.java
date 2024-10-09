package com.food.phat.controller;


import com.food.phat.dto.authentication.TokenResponse;
import com.food.phat.dto.cart.CartResponse;
import com.food.phat.service.CartService;
import com.food.phat.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final CartService cartService;

}
