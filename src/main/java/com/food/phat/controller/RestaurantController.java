package com.food.phat.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class RestaurantController {
    @RequestMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Object> test(@PathVariable Integer restaurantId) {

        return null;
    }
}
