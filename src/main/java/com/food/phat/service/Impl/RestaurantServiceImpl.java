package com.food.phat.service.Impl;


import com.food.phat.dto.restaurant.RestaurantPost;
import com.food.phat.dto.restaurant.RestaurantPut;
import com.food.phat.dto.restaurant.RestaurantResponse;
import com.food.phat.entity.Restaurant;
import com.food.phat.entity.User;
import com.food.phat.mapstruct.restaurant.RestaurantMapper;
import com.food.phat.repository.RestaurantRepository;
import com.food.phat.repository.UserRepository;
import com.food.phat.service.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public void registerRestaurant(RestaurantPost restaurantPost, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Restaurant restaurant = restaurantMapper.toEntity(restaurantPost);
        restaurant.setUser(user);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateRestaurant(RestaurantPut restaurantPut, Integer userId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantPut.getRestaurantId()).get();
        restaurantMapper.updateEntity(restaurantPut, restaurant);
    }

    @Override
    public void deleteRestaurant(Integer restaurantId, Integer userId) {
        restaurantRepository.delete(restaurantId, userId);
    }

    @Override
    public RestaurantResponse getRestaurantById(Integer restaurantId, Integer userId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId, userId);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }
}
