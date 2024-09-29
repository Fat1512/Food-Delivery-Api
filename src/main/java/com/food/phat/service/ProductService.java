package com.food.phat.service;

import com.food.phat.dto.product.ProductReponse;
import com.food.phat.dto.product.ProductRequest;
import com.food.phat.dto.PageResponse;
import com.food.phat.entity.Product;

import java.util.Map;

public interface ProductService {
    PageResponse<Product> getAllProducts(Map<String, String> filteredCondition);
    ProductReponse getProductById(int id);
    ProductReponse update(ProductRequest product);
    ProductReponse save(ProductRequest product);
    void deleteProductById(int id);
}
