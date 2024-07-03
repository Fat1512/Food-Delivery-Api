package com.food.phat.repository;

import com.food.phat.entity.Product;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ProductRepository {
    Page<Product> getAllProducts(Map<String, String> filteredConditions);
    Product getProductById(int id);
    Product getProductByName(String name);
    Product save(Product product);

}
