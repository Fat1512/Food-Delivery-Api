package com.food.phat.controller;

import com.food.phat.dto.menu.MenuCategoryRequest;
import com.food.phat.dto.menu.MenuCategoryResponse;
import com.food.phat.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;

    @Autowired
    public MenuCategoryController(MenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }

    @GetMapping("restaurants/{restaurantId}/menu-categories")
    public ResponseEntity<?> getCategories(@PathVariable Integer restaurantId) {
        List<MenuCategoryResponse> menuCategoryResponses = menuCategoryService.getCategories(restaurantId);
        return new ResponseEntity<>(menuCategoryResponses, HttpStatus.OK);
    }

    @PostMapping("restaurants/{restaurantId}/menu-categories")
    public ResponseEntity<?> createCategory(@PathVariable Integer restaurantId,
                                            @RequestBody MenuCategoryRequest menuCategoryRequest) {
        menuCategoryService.createCategory(menuCategoryRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("menu-categories/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable Integer categoryId) {
        MenuCategoryResponse menuCategoryResponse = menuCategoryService.getCategory(categoryId);
        return new ResponseEntity<>(menuCategoryResponse, HttpStatus.OK);
    }

    @DeleteMapping("menu-categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {
        menuCategoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("menu-categories/{categoryId}/products")
    public ResponseEntity<?> addCategoryProduct(@PathVariable Integer categoryId, @RequestBody List<Integer> productIds) {
        menuCategoryService.addCategoryProduct(categoryId, productIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("menu-categories/{categoryId}/products")
    public ResponseEntity<?> removeCategoryProduct(@PathVariable Integer categoryId, @RequestBody List<Integer> productIds) {
        menuCategoryService.removeCategoryProduct(categoryId, productIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("menu-categories/{categoryId}")
    public ResponseEntity<?> modifyMenuCategory(@RequestBody MenuCategoryRequest menuCategoryRequest) {
        menuCategoryService.modifyMenuCategory(menuCategoryRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
