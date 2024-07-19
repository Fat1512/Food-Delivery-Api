package com.food.phat.service;

import com.food.phat.dto.response.PageResponse;
import com.food.phat.entity.Product;

import java.util.Map;

public interface ProductService {
    PageResponse<Product> getAllProducts(Map<String, String> filteredCondition);
    Product getProductById(int id);
    Product getProductByName(String name);
    Product save(Product product);
}
