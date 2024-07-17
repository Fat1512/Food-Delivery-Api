package com.food.phat.repository.custom;

import com.food.phat.entity.Restaurant;

public interface CustomRestaurantRepo {
    Restaurant findRestaurantByCustomerId(int customerId);
}
