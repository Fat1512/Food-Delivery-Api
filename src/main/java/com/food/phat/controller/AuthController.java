package com.food.phat.controller;

import com.food.phat.dto.JwtResponse;
import com.food.phat.dto.LoginRequest;
import com.food.phat.dto.RegisterRequest;
import com.food.phat.entity.Role;
import com.food.phat.entity.User;
import com.food.phat.service.Impl.UserService;
import com.food.phat.service.JwtService;
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
public class AuthController {

    private JwtService jwtService;
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/getting")
    public ResponseEntity<String> getRes() {
        return new ResponseEntity<>("asdd", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = jwtService.generateToken(user);
        return new ResponseEntity<JwtResponse>(new JwtResponse(jwt), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest registerRequest) {

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role("ROLE_CUSTOMER"));
        user.setRoles(roleList);

        user = userService.save(user);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user
                .getRoles()
                .stream()
                .forEach(u -> authorities.add(new SimpleGrantedAuthority(u.getName())));
        //The password cannot be null
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

        String jwt = jwtService.generateToken(userDetails);
        return new ResponseEntity<JwtResponse>(new JwtResponse(jwt), HttpStatus.OK);
    }
}
