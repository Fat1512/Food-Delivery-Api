package com.food.phat.controller;


import com.food.phat.dto.restaurant.RestaurantPost;
import com.food.phat.dto.restaurant.RestaurantPut;
import com.food.phat.service.Impl.UserServiceImpl;
import com.food.phat.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/register")
    public ResponseEntity<?> registerRestaurant(RestaurantPost restaurantPost) {
        restaurantService.registerRestaurant(restaurantPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRestaurant(RestaurantPut restaurantPut) {
        restaurantService.updateRestaurant(restaurantPut);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{restaurantId}")
    public ResponseEntity<?> getRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.getRestaurantById(restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
