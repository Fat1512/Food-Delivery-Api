package com.food.phat.dto.request;


import lombok.Data;

@Data
public class LoginRequest {
    String username, password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
