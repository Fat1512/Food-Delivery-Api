package com.food.phat.repository;

import com.food.phat.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>, JpaSpecificationExecutor<Cart> {
//    @Query(value = "Select cart.* from cart where cart.user_fkey = ?1 ", nativeQuery = true)
//    Cart findByUserId(Integer userId);
    Cart findByUser_UserId(Integer userId);
}
