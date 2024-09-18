package com.food.phat.service.Impl;

import com.food.phat.dto.menu.MenuCategoryRequest;
import com.food.phat.dto.menu.MenuCategoryResponse;
import com.food.phat.entity.MenuCategory;
import com.food.phat.entity.Product;
import com.food.phat.mapstruct.menu.MenuCategoryMapper;
import com.food.phat.repository.MenuCategoryRepository;
import com.food.phat.repository.MenuRepository;
import com.food.phat.repository.ProductRepository;
import com.food.phat.service.MenuCategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;
    private final ProductRepository productRepository;
    private final MenuCategoryMapper menuCategoryMapper;

    @Autowired
    public MenuCategoryServiceImpl(MenuCategoryRepository menuCategoryRepository,
                                   MenuCategoryMapper menuCategoryMapper,
                                   ProductRepository productRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuCategoryMapper = menuCategoryMapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public MenuCategoryResponse getCategory(Integer categoryId) {
        MenuCategory category = menuCategoryRepository.findById(categoryId).orElse(null);
        return menuCategoryMapper.toDto(category);
    }

    @Override
    @Transactional
    public List<MenuCategoryResponse> getCategories(Integer restaurantId) {
        List<MenuCategory> menuCategories = menuCategoryRepository.findByRestaurantId(restaurantId);
        return menuCategories.stream().map(menuCategoryMapper::toDto).toList();
    }

    @Override
    @Transactional
    public void deleteCategory(Integer categoryId) {
        menuCategoryRepository.deleteById(categoryId);
    }

    @Override
    @Transactional
    public void createCategory(MenuCategoryRequest menuCategoryRequest) {
        MenuCategory menuCategory = menuCategoryMapper.toEntity(menuCategoryRequest);
        menuCategoryRepository.save(menuCategory);
    }

    @Override
    @Transactional
    public void addCategoryProduct(Integer categoryId, List<Integer> productIds) {
        MenuCategory menuCategory = menuCategoryRepository.findById(categoryId).orElse(null);
        if(menuCategory == null) return;
        List<Product> productList = productRepository.findAllById(productIds);
        menuCategory.addProduct(productList);

        menuCategoryRepository.save(menuCategory);
    }

    @Override
    @Transactional
    public void removeCategoryProduct(Integer categoryId, List<Integer> productIds) {
        MenuCategory menuCategory = menuCategoryRepository.findById(categoryId).orElse(null);
        if(menuCategory == null) return;
        menuCategory.removeProduct(productIds);

        menuCategoryRepository.save(menuCategory);
    }

    @Override
    @Transactional
    public void modifyMenuCategory(MenuCategoryRequest menuCategoryRequest) {
        MenuCategory menuCategory = menuCategoryRepository.findById(menuCategoryRequest.getMenuCategoryId()).orElse(null);
        menuCategoryMapper.updateEntity(menuCategoryRequest, menuCategory);
        assert menuCategory != null;
        menuCategoryRepository.save(menuCategory);
    }
}
