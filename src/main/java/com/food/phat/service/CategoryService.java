package com.food.phat.service;

import com.food.phat.entity.ProductCategory;

import java.util.List;

public interface CategoryService {
    List<ProductCategory> getAllCategories();
    ProductCategory getCategoryById(int id);
    ProductCategory save(ProductCategory productCategory);
}
