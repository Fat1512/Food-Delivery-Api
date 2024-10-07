package com.food.phat.controller;

import com.food.phat.dto.authentication.TokenResponse;
import com.food.phat.dto.authentication.LoginRequest;
import com.food.phat.dto.authentication.RegisterRequest;
import com.food.phat.entity.Role;
import com.food.phat.entity.User;
import com.food.phat.service.AuthService;
import com.food.phat.service.Impl.UserServiceImpl;
import com.food.phat.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final UserServiceImpl userServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authService.login(loginRequest);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest registerRequest) {

//        return new ResponseEntity<TokenResponse>(new TokenResponse(jwt), HttpStatus.OK);
        return null;
    }
}

