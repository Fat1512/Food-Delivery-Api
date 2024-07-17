package com.food.phat.repository;

import com.food.phat.entity.Cart;
import com.food.phat.repository.custom.CustomCartRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartRepository extends JpaRepository<Cart, Integer>, JpaSpecificationExecutor<Cart>, CustomCartRepo {
}
