package com.food.phat.controller;

import com.food.phat.entity.ProductCategory;
import com.food.phat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
    public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        List<ProductCategory> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<ProductCategory> createProduct(@RequestBody ProductCategory productCategory) {
        ProductCategory responseProductCategory = categoryService.save(productCategory);
        return new ResponseEntity<>(responseProductCategory, HttpStatus.OK);
    }
}
