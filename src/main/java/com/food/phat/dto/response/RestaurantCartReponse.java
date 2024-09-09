package com.food.phat.dto.response;

import lombok.Data;

@Data
public class RestaurantCartReponse {
    private Integer restaurantId;
    private String name;
    private String address;
    private String avatarImage;
    private String backgroundImage;
    private String city;
    private String country;
}
