package com.food.phat.dto.response;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RestaurantResponse {
    private Integer restaurantId;
    private String name;
    private String address;
    private String avatarImage;
    private String backgroundImage;
    private String city;
    private String country;
    private MenuResponse menuResponse;
}
