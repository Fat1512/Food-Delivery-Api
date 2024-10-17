package com.food.phat.service;

import com.food.phat.dto.authentication.LoginRequest;
import com.food.phat.dto.authentication.RegisterRequest;
import com.food.phat.dto.authentication.TokenResponse;

public interface AuthService {
    TokenResponse refreshToken(String refreshToken);
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse register(RegisterRequest registerRequest) ;
    TokenResponse changePassword(String newPassword, String oldPassword, boolean isLogAllOut) ;
    void logout(String token);
}
