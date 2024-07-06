package com.food.phat.dao;

import com.food.phat.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    Category getCategoryByName(String name);
    Category save(Category category);
}
