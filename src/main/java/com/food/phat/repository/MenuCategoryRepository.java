package com.food.phat.repository;

import com.food.phat.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Integer> {
    @Query(value= """
        select mc.* from menu_category mc, menu_has_category mhc, menu m where
        m.restaurant_fkey = ?1 and
        m.menu_id = mhc.menu_fkey and
        mhc.menu_category_fkey = mc.menu_category_id
    """, nativeQuery = true)
    List<MenuCategory> findByRestaurantId(Integer restaurantId);
}
