package com.food.phat.repository;

import com.food.phat.entity.CommentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentProductRepository extends JpaRepository<CommentProduct, Integer> {
    List<CommentProduct> findByProduct_ProductId(Integer productId);
}
