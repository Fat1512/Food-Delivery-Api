package com.food.phat.dto.restaurant;

import lombok.Data;

@Data
public class RestaurantPost {
    private String name;
    private String address;
    private String avatarImage;
    private String backgroundImage;
    private String city;
    private String country;
}
