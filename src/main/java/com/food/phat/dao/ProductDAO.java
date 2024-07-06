package com.food.phat.dao;

import com.food.phat.entity.Product;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ProductDAO {
    Page<Product> getAllProducts(Map<String, String> filteredConditions);
    Product getProductById(int id);
    Product getProductByName(String name);
    Product save(Product product);

}
