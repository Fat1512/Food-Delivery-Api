package com.food.phat.service.Impl;

import com.food.phat.entity.Cart;
import com.food.phat.repository.CartRepository;
import com.food.phat.service.CartService;
import com.food.phat.specification.FilterRequest;
import com.food.phat.specification.Operator;
import com.food.phat.specification.SearchSpecification;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public Cart getCartItem(String username) {
        FilterRequest<Cart> cartRequest = new FilterRequest<>() {
            @Override
            public Expression setExpression(Root root) {
                return root.get("user").get("username");
            }
        };

        cartRequest.setValue(username);
        cartRequest.setOperator(Operator.EQUAL);
        Cart cart =  cartRepository.findOne(new SearchSpecification<>(new ArrayList<>(List.of(cartRequest)))).orElse(null);

        return cart;
    }
}
