package com.food.phat.service.Impl;

import com.food.phat.config.JwtService;
import com.food.phat.dto.authentication.LoginRequest;
import com.food.phat.dto.authentication.RegisterRequest;
import com.food.phat.dto.authentication.TokenResponse;
import com.food.phat.entity.Role;
import com.food.phat.entity.Token;
import com.food.phat.entity.User;
import com.food.phat.repository.TokenRedisRepository;
import com.food.phat.service.AuthService;
import com.food.phat.service.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserServiceImpl userServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetail = (UserDetails) authentication.getPrincipal();

        TokenResponse tokenResponse = jwtService.generateToken(userDetail);

        User user = userServiceImpl.getUserByUsername(userDetail.getUsername());
        Token token = Token.builder()
                .uuid(tokenResponse.getUuid())
                .userKey(user.getUserId())
                .timeToLive(tokenResponse.getTimeToLive())
                .build();
        tokenService.save(token);

        return tokenResponse;
    }

    @Override
    @Transactional
    public TokenResponse register(RegisterRequest registerRequest) throws Exception {

        User user = userServiceImpl.getUserByUsername(registerRequest.getUsername());
        if(user != null) throw new Exception("User with specified username has already existed ! please try a new one");
        if(registerRequest.getPassword() == null || registerRequest.getPassword().length() < 9) throw new Exception("Password length should be at least 9 !");

        user = User.builder()
                .username(registerRequest.getUsername())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .roles(new ArrayList<>(List.of(new Role("ROLE_CUSTOMER"))))
                .build();

        user = userServiceImpl.save(user);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user
                .getRoles()
                .forEach(u -> authorities.add(new SimpleGrantedAuthority(u.getName())));

        //The password cannot be null
        UserDetails userDetail = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

        TokenResponse tokenResponse = jwtService.generateToken(userDetail);

        Token token = Token.builder()
                .uuid(tokenResponse.getUuid())
                .userKey(user.getUserId())
                .timeToLive(tokenResponse.getTimeToLive())
                .build();
        tokenService.save(token);

        return tokenResponse;
    }
}
