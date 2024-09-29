package com.food.phat.mapstruct.restaurant;

import com.food.phat.dto.restaurant.RestaurantCheckoutResponse;
import com.food.phat.dto.restaurant.RestaurantPost;
import com.food.phat.dto.restaurant.RestaurantPut;
import com.food.phat.dto.restaurant.RestaurantResponse;
import com.food.phat.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantCheckoutResponse toRestaurantCheckoutResponse(Restaurant restaurant);
    @Mapping(target="menus", ignore = true)
    RestaurantResponse toRestaurantResponse(Restaurant restaurant);
    Restaurant toEntity(RestaurantPost restaurantPost);
    void updateEntity(RestaurantPut restaurantPut, @MappingTarget Restaurant restaurant);

}
