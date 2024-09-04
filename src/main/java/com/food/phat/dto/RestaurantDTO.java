package com.food.phat.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RestaurantDTO {
    private Integer restaurantId;
    private String name;
    private String address;
    private String avatarImage;
    private String backgroundImage;
    private String city;
    private String country;
    private MenuDTO menuDTO;
}
