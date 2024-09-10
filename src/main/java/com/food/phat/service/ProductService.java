package com.food.phat.service;

import com.food.phat.dto.response.ProductReponse;
import com.food.phat.dto.request.ProductRequest;
import com.food.phat.dto.response.PageResponse;
import com.food.phat.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    PageResponse<Product> getAllProducts(Map<String, String> filteredCondition);
    ProductReponse getProductById(int id);
    ProductReponse saveOrUpdate(ProductRequest product);
    void deleteProductById(int id);
}
