package com.food.phat.repository;

import com.food.phat.entity.OrderCancel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCancelRepository extends JpaRepository<OrderCancel, Integer> {

}
