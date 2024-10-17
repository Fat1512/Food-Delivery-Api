package com.food.phat.controller;

import com.food.phat.config.JwtService;
import com.food.phat.dto.MessageResponse;
import com.food.phat.dto.authentication.TokenResponse;
import com.food.phat.dto.authentication.LoginRequest;
import com.food.phat.dto.authentication.RegisterRequest;
import com.food.phat.service.AuthService;
import com.food.phat.utils.ApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authService.login(loginRequest);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_LOGIN.name())
                .data(tokenResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest registerRequest)  {
        TokenResponse tokenResponse = authService.register(registerRequest);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_REGISTER.name())
                .data(tokenResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/password")
    public ResponseEntity<MessageResponse> changePassword(@RequestBody Map<String, String> params)  {
        TokenResponse tokenResponse =  authService.changePassword(params.get("newPassword")
                , params.get("oldPassword")
                , Boolean.parseBoolean(params.get("isLogAllOut")));
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_UPDATED.name())
                .data(tokenResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(@RequestHeader("Authorization") String token) {
        token = jwtService.extractToken(token);
        authService.logout(token);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_LOGOUT.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<MessageResponse> refreshToken(@RequestBody Map<String, String> params) {
        TokenResponse tokenResponse = authService.refreshToken(params.get("refreshToken"));
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(tokenResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}

