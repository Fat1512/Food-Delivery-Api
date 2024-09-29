package com.food.phat.repository;

import com.food.phat.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findAllByRestaurant_RestaurantId(Integer restaurantId);
}
