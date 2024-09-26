package com.food.phat.controller;


import com.food.phat.dto.ProductDocument;
import com.food.phat.entity.Product;
import com.food.phat.repository.ProductRepository;
import com.food.phat.repository.TestElasticSearch;
import com.food.phat.service.CartService;
import com.food.phat.service.ProductCategoryService;
import com.food.phat.service.ProductService;
import com.food.phat.service.RestaurantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    ProductCategoryService productCategoryService;

    @Autowired
    TestElasticSearch testElasticSearch;

    @GetMapping("/getting")
    public ResponseEntity<?> getAPI(@RequestBody Map<String, String> params) {
        List<ProductDocument> x = testElasticSearch.findByName(params.get("name"));
        return new ResponseEntity<>(x, HttpStatus.OK);
    }

    @PostMapping("/posting")
    @Transactional
    public ResponseEntity<?> postAPI(@RequestBody ProductDocument productDocument) {
        ProductDocument ProductDocument = testElasticSearch.save(productDocument);
//        return new ResponseEntity<>(ProductDocument, HttpStatus.OK);
        return null;
    }

    @DeleteMapping ("/deleting")
    public ResponseEntity<String> deleteAPI() {
        return new ResponseEntity<>("asdd", HttpStatus.OK);
    }
}
