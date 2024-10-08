package com.food.phat.repository;

import com.food.phat.entity.Order;
import com.food.phat.entity.OrderCancel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser_UserId(Integer userId);

    @Query(value = """
        select * from order o where
        o.order_id = ?1 and
        o.restaurant_fkey = ?2
    """, nativeQuery = true)
    Order findByIdAndRestaurantId(Integer orderId, Integer restaurantId);
}
