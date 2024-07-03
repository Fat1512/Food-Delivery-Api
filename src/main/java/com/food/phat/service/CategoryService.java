package com.food.phat.service;

import com.food.phat.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    Category getCategoryByName(String name);
    Category save(Category category);
}
