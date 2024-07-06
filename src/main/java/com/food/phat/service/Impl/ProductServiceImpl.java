package com.food.phat.service.Impl;

import com.food.phat.dto.PageResponse;
import com.food.phat.entity.Product;
import com.food.phat.repository.ProductRepository;
import com.food.phat.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    @Transactional
    public PageResponse<Product> getAllProducts(Map<String, String> filteredCondition) {
        int page = Integer.parseInt(filteredCondition.get("page"));
        int size = Integer.parseInt(filteredCondition.get("size"));

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        if(products.getTotalElements() == 0) {

        }

        return new PageResponse<Product>(
                products.getContent(),
                page,
                size,
                products.getTotalElements(),
                products.getTotalPages(),
                products.isLast());
    }

    @Override
    @Transactional
    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
