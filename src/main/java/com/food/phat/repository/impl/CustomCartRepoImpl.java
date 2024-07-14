package com.food.phat.repository.impl;

import com.food.phat.repository.CustomCartRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CustomCartRepoImpl implements CustomCartRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List getCartItems(String username) {

        //Get restaurant ID
        List restaurantIdList = em.createQuery("""
            select distinct p.restaurant.id 
            from User u, Cart c, CartDetail cd, Product p
            where u.username LIKE :username and
            c.cartId = cd.cart.cartId and 
            cd.product.id = p.productId
        """).setParameter("username", username).getResultList();

        //Get product
        List q1 = em.createNativeQuery("""
            select
            p.product_id,
               p.name,
               p.description,
               p.status,
               p.thumbnail,
               cd.price as product_price
            from product p, cart_detail cd
            where
            p.restaurant_fkey = ?1 and
            cd.cart_fkey = ?2 and
            cd.product_fkey = p.product_id
        """).setParameter(1, 1).setParameter(2, 1).getResultList();

        //Get corresponding product modifier
        List q2 = em.createNativeQuery("""
            select m.title, mo.title, mo.price from cart_detail cd, modifier m, modifier_option mo, cart_modifier cm
            where
            cd.product_fkey = 1 and
            cd.cart_detail_id = cm.cart_detail_fkey and
            cm.modifier_option_fkey = mo.modifier_option_id and
            mo.modifier_fkey = m.modifier_id;
        """).getResultList();

        return restaurantIdList;
    }
}
