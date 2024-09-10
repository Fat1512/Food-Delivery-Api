package com.food.phat.mapstruct;

import com.food.phat.dto.response.RestaurantCartResponse;
import com.food.phat.entity.Restaurant;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantCartResponse toRestaurantCartResponseDto(Restaurant restaurant);
}
