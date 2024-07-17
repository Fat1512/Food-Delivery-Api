package com.food.phat.repository;

import com.food.phat.entity.Restaurant;
import com.food.phat.repository.custom.CustomCartRepo;
import com.food.phat.repository.custom.CustomRestaurantRepo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>, CustomRestaurantRepo {
}
