package com.food.phat.dao;

import com.food.phat.entity.ProductCategory;

import java.util.List;

public interface CategoryDAO {
    List<ProductCategory> getAllCategories();
    ProductCategory getCategoryById(int id);
    ProductCategory getCategoryByName(String name);
    ProductCategory save(ProductCategory productCategory);
}
