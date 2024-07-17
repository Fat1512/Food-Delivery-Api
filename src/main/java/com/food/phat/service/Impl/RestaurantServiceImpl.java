package com.food.phat.service.Impl;


import com.food.phat.entity.Restaurant;
import com.food.phat.repository.RestaurantRepository;
import com.food.phat.service.RestaurantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional
    public Restaurant getRestaurantByCustomerId(Integer customerId) {
        return restaurantRepository.findRestaurantByCustomerId(customerId);
    }
}
