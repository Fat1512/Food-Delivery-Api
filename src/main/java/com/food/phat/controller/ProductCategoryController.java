package com.food.phat.controller;

import com.food.phat.dto.MessageResponse;
import com.food.phat.entity.ProductCategory;
import com.food.phat.service.ProductCategoryService;
import com.food.phat.utils.ApiResponseMessage;
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
    public ResponseEntity<MessageResponse> getAllCategories(@PathVariable Integer restaurantId) {
        List<ProductCategory> categories = productCategoryService.getAllCategories(restaurantId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(categories)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/restaurants/{restaurantId}/categories")
    public ResponseEntity<MessageResponse> createProductCategory(@RequestBody ProductCategory productCategory, @PathVariable Integer restaurantId) {
        productCategoryService.createCategory(productCategory);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<MessageResponse> modifyCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.updateCategory(productCategory);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_UPDATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<MessageResponse> deleteCategory(@PathVariable Integer categoryId) {
        productCategoryService.deleteCategory(categoryId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_UPDATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}
