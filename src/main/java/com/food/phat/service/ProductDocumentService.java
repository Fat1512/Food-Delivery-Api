package com.food.phat.service;

import com.food.phat.dto.product.ProductResponse;

import java.util.List;
import java.util.Map;

public interface ProductDocumentService {
    List<ProductResponse> getProductsByKeyword(String keyword);
    List<?> getProducts(Map<String, String> params);
    ProductResponse getProduct(Integer productId);
}
