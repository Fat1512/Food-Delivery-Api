package com.food.phat.repository;

import com.food.phat.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByName(String name);
//    Page<Product> findAll(Specification<Product> specs, Pageable pageable);
}
