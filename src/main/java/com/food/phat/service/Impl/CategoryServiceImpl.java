package com.food.phat.service.Impl;

import com.food.phat.entity.ProductCategory;
import com.food.phat.repository.CategoryRepository;
import com.food.phat.service.CategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, EntityManager entityManager) {
        this.categoryRepository = categoryRepository;
        this.em = entityManager;
    }

    @Override
    @Transactional
    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public ProductCategory getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }
}
