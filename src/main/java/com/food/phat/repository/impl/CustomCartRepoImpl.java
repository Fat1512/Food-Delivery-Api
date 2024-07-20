//package com.food.phat.repository.impl;
//
//import com.food.phat.dto.response.cart.CartDetailResponse;
//import com.food.phat.dto.response.cart.CartResponse;
//import com.food.phat.dto.response.ProductResponse;
//import com.food.phat.repository.custom.CustomCartRepo;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class CustomCartRepoImpl implements CustomCartRepo {
//
//    @PersistenceContext
//    private EntityManager em;
//
////    @Override
//    public CartResponse getCart(int userId) {
//
//        //Get restaurant ID
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
//
//        //Get cart ID
//        Integer cartID = (Integer) em.createNativeQuery("""
//            select cart_id from cart, user
//            where
//            cart.user_fkey = user.user_id and
//            user.user_id = ?1
//        """).setParameter(1, userId).getSingleResult();
//
//        CartResponse cartResponse = new CartResponse();
//        cartResponse.setCartId(cartID);
//
//
//        restaurantIdList.forEach(id -> {
//            CartDetailResponse cartItemResponse = new CartDetailResponse();
//            cartItemResponse.setRestaurantId((Integer) id[0]);
//            cartItemResponse.setRestaurantName((String) id[1]);
//            //Get product
//            List<Object[]> productList = em.createNativeQuery("""
//                select
//                    p.product_id,
//                    p.name,
//                    p.description,
//                    p.status,
//                    p.thumbnail,
//                    cd.price as product_price
//                from product p, cart_detail cd
//                where
//                p.restaurant_fkey = ?1 and
//                cd.cart_fkey = ?2 and
//                cd.product_fkey = p.product_id
//            """).setParameter(1, (Integer) id[0]).setParameter(2, cartID).getResultList();
//
//            List<ProductResponse> productResponseList  = productList.stream().map(prod -> {
//                List<Object[]> productModifier = em.createNativeQuery("""
//                    select m.title, mo.title, mo.price from cart_detail cd, modifier m, modifier_option mo, cart_modifier cm
//                    where
//                    cd.product_fkey = ?1 and
//                    cd. = cm.cart_detail_fkey and
//                    cm.modifier_option_fkey = mo.modifier_option_id and
//                    mo.modifier_fkey = m.modifier_id
//                """).setParameter(1, prod[0]).getResultList();
//
//                ProductResponse prodRes = new ProductResponse();
//                prodRes.setProductId((Integer) prod[0]);
//                prodRes.setName((String) prod[1]);
//                prodRes.setDescription((String) prod[2]);
//                prodRes.setStatus((Boolean) prod[3]);
//                prodRes.setThumbnail((String) prod[4]);
//                prodRes.setPrice((Float) prod[5]);
//                prodRes.setModifiers(productModifier);
//                return prodRes;
//            }).collect(Collectors.toList());
//
////            cartItemResponse.setProducts(productResponseList);
//            cartResponse.addCartItem(cartItemResponse);
//        });
//        return cartResponse;
//    }
//}
