package com.food.phat.service;

import com.food.phat.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getRestaurantByCustomerId(Integer customerId);
}
