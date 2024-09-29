package com.food.phat.service.Impl;

import com.food.phat.entity.ProductCategory;
import com.food.phat.repository.ProductCategoryRepository;
import com.food.phat.service.ProductCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    @Transactional
    public List<ProductCategory> getAllCategories(Integer restaurantId) {
        return productCategoryRepository.findAllByRestaurant_RestaurantId(restaurantId);
    }

    @Override
    @Transactional
    public ProductCategory createCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    @Transactional
    public void updateCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Integer categoryId) {
        productCategoryRepository.deleteById(categoryId);
    }
}