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
import com.food.phat.utils.AuthenticationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public void registerRestaurant(RestaurantPost restaurantPost) {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());

        Restaurant restaurant = restaurantMapper.toEntity(restaurantPost);
        restaurant.setUser(user);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateRestaurant(RestaurantPut restaurantPut) {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());

        Restaurant restaurant = restaurantRepository.findById(restaurantPut.getRestaurantId(), user.getUserId());
        restaurantMapper.updateEntity(restaurantPut, restaurant);
    }

    @Override
    public void deleteRestaurant(Integer restaurantId) {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());

        restaurantRepository.delete(restaurantId, user.getUserId());
    }

    @Override
    public RestaurantResponse getRestaurantById(Integer restaurantId) {
        Authentication authentication = AuthenticationUtil.getAuthentication();
        User user = userRepository.findByUsername(((Principal)authentication.getPrincipal()).getName());

        Restaurant restaurant = restaurantRepository.findById(restaurantId, user.getUserId());
        return restaurantMapper.toRestaurantResponse(restaurant);
    }
}
