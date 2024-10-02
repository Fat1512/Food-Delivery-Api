package com.food.phat.mapstruct.cart.decorator;

import com.food.phat.dto.cart.CartItemPost;
import com.food.phat.dto.cart.CartItemPut;
import com.food.phat.dto.cart.CartItemResponse;
import com.food.phat.entity.CartItem;
import com.food.phat.entity.CartModifier;
import com.food.phat.entity.Product;
import com.food.phat.mapstruct.cart.CartItemMapper;
import com.food.phat.mapstruct.cart.CartModifierMapper;
import com.food.phat.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper
public abstract class CartItemDecorator implements CartItemMapper {
    @Qualifier("delegate")
    @Autowired
    private CartItemMapper delegate;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartModifierMapper cartModifierMapper;

    @Override
    public CartItemResponse toDto(CartItem cartItem) {
        CartItemResponse cartItemResponse = delegate.toDto(cartItem);
        cartItemResponse.setModifierGroups(cartModifierMapper.toDto(cartItem.getModifiers()));
        return cartItemResponse;
    }

    @Override
    public CartItem toEntity(CartItemPost cartItemPost) {
        CartItem cartItem = delegate.toEntity(cartItemPost);

        List<CartModifier> modifiers = cartModifierMapper.toEntity(cartItem, cartItemPost.getModifierGroups());
        Product product = productRepository.findById(cartItemPost.getProductId()).get();
        cartItem.setProduct(product);
        cartItem.setModifiers(modifiers);
        return cartItem;
    }

    @Override
    public void updateEntity(CartItemPut cartItemPut, CartItem cartItem) {
        delegate.updateEntity(cartItemPut, cartItem);
        List<CartModifier> cartModifiers = cartModifierMapper.toEntity(cartItem, cartItemPut.getModifierGroups());
        cartItem.updateCartModifier(cartModifiers);
        cartItem.setQty(cartItemPut.getQty());
    }
}
