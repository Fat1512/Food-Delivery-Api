package com.food.phat.service;

import com.food.phat.dto.authentication.LoginRequest;
import com.food.phat.dto.authentication.RegisterRequest;
import com.food.phat.dto.authentication.TokenResponse;

public interface AuthService {
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse register(RegisterRequest registerRequest) throws Exception;
}
