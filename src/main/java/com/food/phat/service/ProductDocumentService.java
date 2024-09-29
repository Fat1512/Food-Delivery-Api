package com.food.phat.service;

import com.food.phat.dto.product.ProductResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductDocumentService {
    List<?> getProducts(Map<String, String> params) throws IOException;
}
