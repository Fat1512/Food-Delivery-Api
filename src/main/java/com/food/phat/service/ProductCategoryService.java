package com.food.phat.service;

import com.food.phat.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getAllCategories(Integer restaurantId);
    void createCategory(ProductCategory productCategory);
    void updateCategory(ProductCategory productCategory);
    void deleteCategory(Integer categoryId);
}
