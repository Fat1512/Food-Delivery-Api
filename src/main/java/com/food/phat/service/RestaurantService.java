package com.food.phat.service;

import com.food.phat.dto.restaurant.RestaurantPost;
import com.food.phat.dto.restaurant.RestaurantPut;
import com.food.phat.dto.restaurant.RestaurantResponse;
import com.food.phat.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    void registerRestaurant(RestaurantPost restaurantId);
    void updateRestaurant(RestaurantPut restaurant);
    void deleteRestaurant(Integer restaurantId);
    RestaurantResponse getRestaurantById(Integer restaurantId);
}
