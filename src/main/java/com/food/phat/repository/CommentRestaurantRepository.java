package com.food.phat.repository;

import com.food.phat.entity.Comment;
import com.food.phat.entity.CommentRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRestaurantRepository extends JpaRepository<CommentRestaurant, Integer> {
    List<CommentRestaurant> findByRestaurant_RestaurantId(Integer restaurantId);
}
