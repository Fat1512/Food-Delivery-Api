package com.food.phat.dto.restaurant;


import com.food.phat.dto.menu.MenuResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private List<Integer> menus;
}
