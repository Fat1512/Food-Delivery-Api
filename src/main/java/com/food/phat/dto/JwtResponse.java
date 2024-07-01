package com.food.phat.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String accessToken;

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
