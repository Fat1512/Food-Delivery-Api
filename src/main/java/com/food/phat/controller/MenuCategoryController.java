package com.food.phat.controller;

import com.food.phat.dto.menu.MenuCategoryRequest;
import com.food.phat.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
public class MenuCategoryController {

    private MenuCategoryService menuCategoryService;

    @Autowired
    public MenuCategoryController(MenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }

    @GetMapping("/{restaurantId}/menus/categories/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable Integer categoryId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}/menus/categories")
    public ResponseEntity<?> getCategories(@PathVariable Integer restaurantId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}/menus/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{restaurantId}/menus/categories")
    public ResponseEntity<?> createCategory(@PathVariable Integer restaurantId,
                                            @RequestBody MenuCategoryRequest menuCategoryRequest) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{restaurantId}/menus/categories/{categoryId}/products")
    public ResponseEntity<?> addCategoryProduct(@RequestBody List<Integer> productIds) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}/menus/categories/{categoryId}/products")
    public ResponseEntity<?> removeCategoryProduct(@RequestBody List<Integer> productIds) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}/menus/categories/{categoryId}")
    public ResponseEntity<?> modifyMenuCategory(@RequestBody MenuCategoryRequest menuCategoryRequest) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
