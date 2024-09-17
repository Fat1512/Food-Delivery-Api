package com.food.phat.repository;

import com.food.phat.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query(value = """
        select * from menu m where 
        m.menu_id = ?2 and
        m.restaurant_fkey = ?1 
    """,nativeQuery = true)
    Menu findByRestauranIdAndMenuId(Integer restaurantId, Integer menuId);


    @Query(value = "select * from menu where menu.restaurant_fkey=?1", nativeQuery = true)
    List<Menu> findAllByRestaurantId(Integer restaurantId);

    @Query(value = """
        delete from menu m where 
        m.menu_id = ?2 and
        m.restaurant_fkey = ?1 
    """,nativeQuery = true)
    void deleteByRestaurantIdAndMenuId(Integer restaurantId, Integer menuId);
}
