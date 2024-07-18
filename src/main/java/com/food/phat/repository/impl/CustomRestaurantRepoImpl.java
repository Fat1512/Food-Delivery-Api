package com.food.phat.repository.impl;

import com.food.phat.entity.Restaurant;
import com.food.phat.repository.custom.CustomRestaurantRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomRestaurantRepoImpl implements CustomRestaurantRepo {

    @PersistenceContext
    private EntityManager em;

//    @Override
    public Restaurant findRestaurantByCustomerId(int customerId) {
//        List<Object[]> restaurantIdList = em.createNativeQuery("""
//            select distinct res.restaurant_id, res.name
//            from user u, cart c, cart_detail cd, product p, restaurant res
//            where
//            u.user_id = ?1 and
//            u.user_id = c.user_fkey and
//            c.cart_id = cd.cart_fkey and
//            cd.product_fkey = p.product_id and
//           p.restaurant_fkey = res.restaurant_id
//        """).setParameter(1, userId).getResultList();

//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
//
//        Root<User> userRoot = criteriaQuery.from(User.class);
//        Join<User, Cart> cartJoin = userRoot.join("cart", JoinType.INNER);
//        Join<Cart, CartDetail> cartDetailJoin = cartJoin.join("cartDetail", JoinType.INNER);
//        Join<CartDetail, Product> productJoin = cartDetailJoin.join("product", JoinType.INNER);
//        Join<Product, Restaurant> restaurantJoin = productJoin.join("restaurant", JoinType.INNER);
//
//        criteriaQuery.multiselect(
//                        restaurantJoin.get("restaurantId"),
//                        restaurantJoin.get("name")
//                )
//                .distinct(true)
//                .where(criteriaBuilder.equal(userRoot.get("userId"), customerId));
//
//        List<Object[]> restaurantIdList = em.createQuery(criteriaQuery).setParameter(1, customerId).getResultList();

        return null;
    }
}


























