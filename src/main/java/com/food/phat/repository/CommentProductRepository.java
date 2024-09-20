package com.food.phat.repository;

import com.food.phat.entity.CommentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentProductRepository extends JpaRepository<CommentProduct, Integer> {
}
