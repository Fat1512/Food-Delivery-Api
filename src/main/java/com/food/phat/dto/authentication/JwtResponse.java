package com.food.phat.dto.authentication;

import lombok.Data;

@Data
public class JwtResponse {
    private String accessToken;

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
