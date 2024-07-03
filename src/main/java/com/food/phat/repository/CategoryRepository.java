package com.food.phat.repository;

import com.food.phat.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    Category getCategoryByName(String name);
    Category save(Category category);
}
