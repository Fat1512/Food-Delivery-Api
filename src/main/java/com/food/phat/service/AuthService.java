package com.food.phat.service;

import com.food.phat.dto.authentication.LoginRequest;
import com.food.phat.dto.authentication.RegisterRequest;
import com.food.phat.dto.authentication.TokenResponse;

public interface AuthService {
    TokenResponse refreshToken(String refreshToken) throws Exception;
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse register(RegisterRequest registerRequest) throws Exception;
    TokenResponse changePassword(String newPassword, String oldPassword, boolean isLogAllOut) throws Exception;
    void logout(String token);
}
