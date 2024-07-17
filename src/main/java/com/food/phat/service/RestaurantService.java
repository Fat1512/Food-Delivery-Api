package com.food.phat.service;

import com.food.phat.entity.Restaurant;

public interface RestaurantService {
    Restaurant getRestaurantByCustomerId(Integer customerId);
}
