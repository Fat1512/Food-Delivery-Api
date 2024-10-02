package com.food.phat.dto.authentication;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username, password, lastName, firstName, email;
    public RegisterRequest(String username, String password, String lastName, String firstName, String email) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }
}
