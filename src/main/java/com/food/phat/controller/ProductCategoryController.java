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
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("{restaurantId}/categories")
    public ResponseEntity<List<ProductCategory>> getAllCategories(@PathVariable Integer restaurantId) {
        List<ProductCategory> categories = productCategoryService.getAllCategories(restaurantId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("{restaurantId}/categories")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory, @PathVariable Integer restaurantId) {
        ProductCategory responseProductCategory = productCategoryService.createCategory(productCategory);
        return new ResponseEntity<>(responseProductCategory, HttpStatus.OK);
    }

    @PutMapping("{restaurantId}/categories")
    public ResponseEntity<ProductCategory> modifyCategory(@RequestBody ProductCategory productCategory, @PathVariable Integer restaurantId) {
        productCategoryService.updateCategory(productCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{restaurantId}/categories/{categoryId}")
    public ResponseEntity<ProductCategory> modifyCategory(@PathVariable Integer categoryId, @PathVariable Integer restaurantId) {
        productCategoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
