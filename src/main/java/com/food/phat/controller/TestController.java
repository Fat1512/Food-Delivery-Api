package com.food.phat.controller;


import com.food.phat.service.CartService;
import com.food.phat.service.CategoryService;
import com.food.phat.service.ProductService;
import com.food.phat.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    @GetMapping("/getting")
    public ResponseEntity<String> getAPI() {
        return new ResponseEntity<>("asdd", HttpStatus.OK);
    }

    @PostMapping("/posting")
    public ResponseEntity<String> postAPI() {
        return new ResponseEntity<>("asdd", HttpStatus.OK);
    }

    @DeleteMapping ("/deleting")
    public ResponseEntity<String> deleteAPI() {
        return new ResponseEntity<>("asdd", HttpStatus.OK);
    }
}
