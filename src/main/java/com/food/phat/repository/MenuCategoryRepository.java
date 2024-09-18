package com.food.phat.repository;

import com.food.phat.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Integer> {
    @Query(value= """
        select * from menu_category mc where
        mc.restaurant_fkey = ?1
    """, nativeQuery = true)
    List<MenuCategory> findByRestaurantId(Integer restaurantId);
}
