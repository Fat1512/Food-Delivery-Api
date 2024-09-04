package com.food.phat.repository;

import com.food.phat.entity.ModifierGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModifierRepository extends JpaRepository<ModifierGroup, Integer> {
}
