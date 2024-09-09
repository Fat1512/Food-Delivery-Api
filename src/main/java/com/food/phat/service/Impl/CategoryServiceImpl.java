package com.food.phat.service.Impl;

import com.food.phat.entity.ProductCategory;
import com.food.phat.repository.ProductCategoryRepository;
import com.food.phat.service.CategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private ProductCategoryRepository productCategoryRepository;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    public CategoryServiceImpl(ProductCategoryRepository productCategoryRepository, EntityManager entityManager) {
        this.productCategoryRepository = productCategoryRepository;
        this.em = entityManager;
    }

    @Override
    @Transactional
    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    @Transactional
    public ProductCategory getCategoryById(int id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
