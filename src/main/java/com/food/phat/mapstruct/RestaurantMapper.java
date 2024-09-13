package com.food.phat.mapstruct;

import com.food.phat.dto.restaurant.RestaurantCheckoutResponse;
import com.food.phat.entity.Restaurant;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantCheckoutResponse toRestaurantCheckoutResponse(Restaurant restaurant);
}
