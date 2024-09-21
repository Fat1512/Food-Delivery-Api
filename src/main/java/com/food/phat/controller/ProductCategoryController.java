package com.food.phat.controller;

import com.food.phat.entity.ProductCategory;
import com.food.phat.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
    public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        List<ProductCategory> categories = productCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<ProductCategory> createProduct(@RequestBody ProductCategory productCategory) {
        ProductCategory responseProductCategory = productCategoryService.save(productCategory);
        return new ResponseEntity<>(responseProductCategory, HttpStatus.OK);
    }
}
