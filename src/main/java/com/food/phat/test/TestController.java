package com.food.phat.test;


import com.food.phat.dto.ProductDocument;
import com.food.phat.repository.ProductDocumentRepository;
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
    ProductDocumentRepository productDocumentRepository;

    @Autowired
    ProducerService producerService;

    @GetMapping("/getting")
    public ResponseEntity<?> getAPI(@RequestBody Map<String, String> params) {
        List<ProductDocument> x = productDocumentRepository.findByName(params.get("name"));
        return new ResponseEntity<>(x, HttpStatus.OK);
    }

    @PostMapping("/posting")
    @Transactional
    public ResponseEntity<?> postAPI(@RequestBody ProductDocument productDocument) {
        ProductDocument ProductDocument = productDocumentRepository.save(productDocument);
        return null;
    }

    @GetMapping("/consume")
    public ResponseEntity<?> consum(@RequestBody String params) {
        producerService.sendMessage(params);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping ("/deleting")
    public ResponseEntity<String> deleteAPI() {
        return new ResponseEntity<>("asdd", HttpStatus.OK);
    }
}
