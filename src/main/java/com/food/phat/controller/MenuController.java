package com.food.phat.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurant")
public class MenuController {

    @GetMapping("/{restaurantId}/menu/{menuId}")
    public ResponseEntity<?> getMenu(@PathVariable Integer restaurantId, @PathVariable Integer menuId) {

        return null;
    }
}
