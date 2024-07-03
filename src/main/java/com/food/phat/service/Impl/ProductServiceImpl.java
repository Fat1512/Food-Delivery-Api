package com.food.phat.service.Impl;

import com.food.phat.dto.PageResponse;
import com.food.phat.entity.Product;
import com.food.phat.repository.CategoryRepository;
import com.food.phat.repository.ProductRepository;
import com.food.phat.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    @Transactional
    public PageResponse<Product> getAllProducts(Map<String, String> filteredConditions) {
        Page<Product> products = productRepository.getAllProducts(filteredConditions);
        PageResponse<Product> productPageResponse = new PageResponse(
                products.getContent(),
                filteredConditions.get("page"),
                filteredConditions.get("size"),
                products.getTotalElements(),
                products.getTotalPages(), products.isLast());
        return productPageResponse;
    }

    @Override
    @Transactional
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    @Transactional
    public Product getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
