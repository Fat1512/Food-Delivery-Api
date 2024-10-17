package com.food.phat.controller;

import com.food.phat.dto.MessageResponse;
import com.food.phat.dto.menu.MenuCategoryRequest;
import com.food.phat.dto.menu.MenuCategoryResponse;
import com.food.phat.service.MenuCategoryService;
import com.food.phat.utils.ApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;

    @GetMapping("restaurants/{restaurantId}/menu-categories")
    public ResponseEntity<MessageResponse> getCategories(@PathVariable Integer restaurantId) {
        List<MenuCategoryResponse> menuCategoryResponses = menuCategoryService.getCategories(restaurantId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(menuCategoryResponses)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("restaurants/{restaurantId}/menu-categories")
    public ResponseEntity<MessageResponse> createCategory(@PathVariable Integer restaurantId,
                                            @RequestBody MenuCategoryRequest menuCategoryRequest) {
        menuCategoryService.createCategory(menuCategoryRequest);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @GetMapping("menu-categories/{categoryId}")
    public ResponseEntity<MessageResponse> getCategory(@PathVariable Integer categoryId) {
        MenuCategoryResponse menuCategoryResponse = menuCategoryService.getCategory(categoryId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(menuCategoryResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @DeleteMapping("menu-categories/{categoryId}")
    public ResponseEntity<MessageResponse> deleteCategory(@PathVariable Integer categoryId) {
        menuCategoryService.deleteCategory(categoryId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_DELETED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);    }

    @PostMapping("menu-categories/{categoryId}/products")
    public ResponseEntity<MessageResponse> addCategoryProduct(@PathVariable Integer categoryId, @RequestBody List<Integer> productIds) {
        menuCategoryService.addCategoryProduct(categoryId, productIds);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);    }

    @DeleteMapping("menu-categories/{categoryId}/products")
    public ResponseEntity<MessageResponse> removeCategoryProduct(@PathVariable Integer categoryId, @RequestBody List<Integer> productIds) {
        menuCategoryService.removeCategoryProduct(categoryId, productIds);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_DELETED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);    }

    @PutMapping("menu-categories/{categoryId}")
    public ResponseEntity<MessageResponse> modifyMenuCategory(@RequestBody MenuCategoryRequest menuCategoryRequest) {
        menuCategoryService.modifyMenuCategory(menuCategoryRequest);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_UPDATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
