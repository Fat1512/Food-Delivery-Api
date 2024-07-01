package com.food.phat.dto;


import lombok.Data;

@Data
public class LoginRequest {
    String username, password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
