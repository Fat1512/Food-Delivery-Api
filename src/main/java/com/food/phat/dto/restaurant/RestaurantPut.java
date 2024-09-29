package com.food.phat.dto.restaurant;

import lombok.Data;

@Data
public class RestaurantPut {
    private Integer restaurantId;
    private String name;
    private String address;
    private String avatarImage;
    private String backgroundImage;
    private String city;
    private String country;
}
