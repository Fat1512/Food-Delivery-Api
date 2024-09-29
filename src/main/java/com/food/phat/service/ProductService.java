package com.food.phat.service;

import com.food.phat.dto.product.ProductResponse;
import com.food.phat.dto.product.ProductRequest;
import com.food.phat.dto.PageResponse;
import com.food.phat.entity.Product;

import java.util.Map;

public interface ProductService {
    PageResponse<Product> getAllProducts(Map<String, String> filteredCondition);
    ProductResponse getProductById(int id);
    ProductResponse update(ProductRequest product);
    ProductResponse save(ProductRequest product);
    void deleteProductById(int id);
}
