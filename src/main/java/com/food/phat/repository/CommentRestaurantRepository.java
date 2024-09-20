package com.food.phat.repository;

import com.food.phat.entity.CommentRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRestaurantRepository extends JpaRepository<CommentRestaurant, Integer> {
}
