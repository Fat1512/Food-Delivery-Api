package com.food.phat.controller;

import com.food.phat.entity.ProductCategory;
import com.food.phat.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("/restaurants/{restaurantId}/categories")
    public ResponseEntity<List<ProductCategory>> getAllCategories(@PathVariable Integer restaurantId) {
        List<ProductCategory> categories = productCategoryService.getAllCategories(restaurantId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/restaurants/{restaurantId}/categories")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory, @PathVariable Integer restaurantId) {
        ProductCategory responseProductCategory = productCategoryService.createCategory(productCategory);
        return new ResponseEntity<>(responseProductCategory, HttpStatus.OK);
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<ProductCategory> modifyCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.updateCategory(productCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<ProductCategory> modifyCategory(@PathVariable Integer categoryId) {
        productCategoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
