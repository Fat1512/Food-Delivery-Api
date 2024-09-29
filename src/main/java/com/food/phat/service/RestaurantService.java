package com.food.phat.service;

import com.food.phat.dto.restaurant.RestaurantPost;
import com.food.phat.dto.restaurant.RestaurantPut;
import com.food.phat.dto.restaurant.RestaurantResponse;
import com.food.phat.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    void registerRestaurant(RestaurantPost restaurantId, Integer userId);
    void updateRestaurant(RestaurantPut restaurant, Integer userId);
    void deleteRestaurant(Integer restaurantId, Integer userId);
    RestaurantResponse getRestaurantById(Integer restaurantId, Integer userId);
}
