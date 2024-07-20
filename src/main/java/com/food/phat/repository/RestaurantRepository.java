package com.food.phat.repository;

import com.food.phat.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query(value = """
        select distinct res.*
            from user u, cart c, cart_detail cd, product p, restaurant res
            where
            u.user_id = ?1 and
            u.user_id = c.user_fkey and
            c.cart_id = cd.cart_fkey and
            cd.product_fkey = p.product_id and
           p.restaurant_fkey = res.restaurant_id
    """, nativeQuery = true)
    List<Restaurant> getRestaurantByCustomerId(Integer customerId);
}
