package com.food.phat.repository;

import com.food.phat.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query(value= """
        select cart_item.* from cart, cart_item where
            cart.user_fkey = ?2 and
            cart.cart_id = cart_item.cart_fkey and
            cart_item.cart_item_id in ?1""", nativeQuery = true)
    List<CartItem> findAllById(Iterable<Integer> cartItemIds, Integer userId);

    @Query(value= """
        select cart_item.* from cart, cart_item where
            cart.user_fkey = ?2 and
            cart.cart_id = cart_item.cart_fkey and
            cart_item.cart_item_id in ?1
    """, nativeQuery = true)
    CartItem findByIdAndUserId(Integer cartItemId, Integer userId);
}
