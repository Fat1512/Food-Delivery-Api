package com.food.phat.repository;

import com.food.phat.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerAddress_User_UserId(Integer customerId);
}
