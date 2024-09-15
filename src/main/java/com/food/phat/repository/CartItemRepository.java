package com.food.phat.repository;

import com.food.phat.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query(value= """
        select cart_item.* from cart, cart_item where
            cart.user_fkey = ?2 and
            cart.cart_id = cart_item.cart_fkey and
            cart_item.cart_item_id in ?1
            """, nativeQuery = true)
    List<CartItem> findAllByIdAndUserId(Iterable<Integer> cartItemIds, Integer userId);

    @Query(value= """
        select cart_item.* from cart, cart_item where
            cart.cart_id = cart_item.cart_fkey and
            cart_item.cart_item_id = ?1
            cart.user_fkey = ?2 and
    """, nativeQuery = true)
    CartItem findByIdAndUserId(Integer cartItemId, Integer userId);

    @Modifying
    @Query(value = """
        delete ci from cart c, cart_item ci where 
            ci.cart_fkey = c.cart_id and
            ci.product_fkey in ?1 and
            user_fkey = ?2
    """, nativeQuery = true)
    void deleteAllByProductIdAndUserId(List<Integer> productId, Integer userId);
}
