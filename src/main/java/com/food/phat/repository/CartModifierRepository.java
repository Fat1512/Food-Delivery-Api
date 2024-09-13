package com.food.phat.repository;

import com.food.phat.entity.CartModifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartModifierRepository extends JpaRepository<CartModifier, Integer> {
}
