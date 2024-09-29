package com.food.phat.controller;


import com.food.phat.dto.restaurant.RestaurantPost;
import com.food.phat.dto.restaurant.RestaurantPut;
import com.food.phat.service.Impl.UserService;
import com.food.phat.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerRestaurant(RestaurantPost restaurantPost, Principal principal) {
        Integer userId = userService.getUserByUsername(principal.getName()).getUserId();
        restaurantService.registerRestaurant(restaurantPost, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRestaurant(RestaurantPut restaurantPut, Principal principal) {
        Integer userId = userService.getUserByUsername(principal.getName()).getUserId();
        restaurantService.updateRestaurant(restaurantPut, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Integer restaurantId, Principal principal) {
        Integer userId = userService.getUserByUsername(principal.getName()).getUserId();
        restaurantService.deleteRestaurant(restaurantId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{restaurantId}")
    public ResponseEntity<?> getRestaurant(@PathVariable Integer restaurantId, Principal principal) {
        Integer userId = userService.getUserByUsername(principal.getName()).getUserId();
        restaurantService.getRestaurantById(restaurantId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
