package com.food.phat.controller;

import com.food.phat.config.JwtService;
import com.food.phat.dto.authentication.TokenResponse;
import com.food.phat.dto.authentication.LoginRequest;
import com.food.phat.dto.authentication.RegisterRequest;
import com.food.phat.service.AuthService;
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
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authService.login(loginRequest);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest registerRequest) throws Exception {
        TokenResponse tokenResponse = authService.register(registerRequest);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<TokenResponse> changePassword(@RequestBody Map<String, String> params) throws Exception {
        TokenResponse tokenResponse =  authService.changePassword(params.get("newPassword")
                , params.get("oldPassword")
                , Boolean.parseBoolean(params.get("isLogAllOut")));
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        token = jwtService.extractToken(token);
        authService.logout(token);
        return new ResponseEntity<>("Logout successfully", HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody String refreshToken) throws Exception {
        TokenResponse tokenResponse = authService.refreshToken(refreshToken);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}

