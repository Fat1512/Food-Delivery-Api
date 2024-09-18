package com.food.phat.service;

import com.food.phat.dto.menu.MenuCategoryRequest;
import com.food.phat.dto.menu.MenuCategoryResponse;

import java.util.List;

public interface MenuCategoryService {
    MenuCategoryResponse getCategory(Integer categoryId);
    List<MenuCategoryResponse> getCategories(Integer restaurantId);
    void deleteCategory(Integer categoryId);
    void createCategory(MenuCategoryRequest menuCategoryRequest);
    void addCategoryProduct(Integer categoryId, List<Integer> productIds);
    void removeCategoryProduct(Integer categoryId, List<Integer> productIds);
    void modifyMenuCategory(MenuCategoryRequest menuCategoryRequest);
}
