package com.food.phat.service.Impl;

import com.food.phat.config.JwtService;
import com.food.phat.dto.authentication.LoginRequest;
import com.food.phat.dto.authentication.RegisterRequest;
import com.food.phat.dto.authentication.TokenResponse;
import com.food.phat.entity.Role;
import com.food.phat.entity.Token;
import com.food.phat.entity.User;
import com.food.phat.repository.RoleRepository;
import com.food.phat.repository.TokenRedisRepository;
import com.food.phat.service.AuthService;
import com.food.phat.service.TokenService;
import com.food.phat.utils.AuthenticationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserServiceImpl userServiceImpl;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Value(value = "${app.token.expirationTime}")
    private int expirationTime;

    @Override
    public TokenResponse refreshToken(String refreshToken) throws Exception {

        String uuid = jwtService.extractUuid(refreshToken);
        Token token = tokenService.get(uuid);
        if (token != null) throw new Exception("Access key is still valid !");

        if (!jwtService.validateToken(refreshToken)) {
            throw new Exception("Refresh token invalid or expired");
        }

        User user = userServiceImpl.getUserByUsername(jwtService.extractUsername(refreshToken));
        UserDetails userDetail = getUserDetails(user);
        TokenResponse tokenResponse = jwtService.generateToken(userDetail);

        token = Token.builder()
                .uuid(tokenResponse.getUuid())
                .userKey(user.getUserId())
                .timeToLive(expirationTime)
                .build();
        tokenService.save(token);

        return tokenResponse;
    }

    @Override
    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetail = (UserDetails) authentication.getPrincipal();

        User user = userServiceImpl.getUserByUsername(userDetail.getUsername());
        TokenResponse tokenResponse = jwtService.generateToken(userDetail);

        Token token = Token.builder()
                .uuid(tokenResponse.getUuid())
                .userKey(user.getUserId())
                .timeToLive(expirationTime)
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
                .roles(new ArrayList<>(List.of(roleRepository.findByName("CUSTOMER"))))
                .build();

        user = userServiceImpl.save(user);
        //The password cannot be null
        UserDetails userDetail = getUserDetails(user);

        TokenResponse tokenResponse = jwtService.generateToken(userDetail);
        Token token = Token.builder()
                .uuid(tokenResponse.getUuid())
                .userKey(user.getUserId())
                .timeToLive(expirationTime)
                .build();
        tokenService.save(token);

        return tokenResponse;
    }

    @Override
    @Transactional
    public TokenResponse changePassword(String newPassword, String oldPassword, boolean isLogAllOut) throws Exception {
        Authentication authentication = AuthenticationUtil.getAuthentication();

        if(authentication == null) throw new Exception("Authentication is null !");

        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        User user = userServiceImpl.getUserByUsername(userDetail.getUsername());

        if(user == null) throw new Exception("User not exist !");

        if(!user.getPassword().equals(passwordEncoder.encode(oldPassword)))
            throw new Exception("Specified password doesn't match");

        user.setPassword(passwordEncoder.encode(newPassword));
        userServiceImpl.save(user);

        if(isLogAllOut) {
            tokenService.invalidateAllUserToken(user.getUserId());
        }

        TokenResponse tokenResponse = jwtService.generateToken(userDetail);
        Token token = Token.builder()
                .uuid(tokenResponse.getUuid())
                .userKey(user.getUserId())
                .timeToLive(expirationTime)
                .build();
        tokenService.save(token);

        return tokenResponse;
    }

    @Override
    @Transactional
    public void logout(String token) {
        String uuid = jwtService.extractUuid(token);
        tokenService.delete(uuid);
    }

    private UserDetails getUserDetails(User user) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user
                .getRoles()
                .forEach(u -> authorities.add(new SimpleGrantedAuthority(u.getName())));

        //The password cannot be null
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
