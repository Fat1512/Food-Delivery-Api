package com.food.phat.mapstruct;

import com.food.phat.dto.cart.CartItemPut;
import com.food.phat.dto.cart.CartItemRequest;
import com.food.phat.dto.modifier.ModifierGroupCartRequest;
import com.food.phat.dto.modifier.ModifierGroupResponse;
import com.food.phat.dto.cart.CartItemResponse;
import com.food.phat.entity.*;
import com.food.phat.repository.ModifierGroupRepository;
import com.food.phat.repository.ModifierRepository;
import com.food.phat.repository.ProductRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

@Mapper(componentModel = "spring", uses = ModifierMapper.class, injectionStrategy = InjectionStrategy.FIELD)
@DecoratedWith(CartItemDecorator.class)
public interface CartItemMapper {
    @Mapping(target = "productId", source="product.productId")
    @Mapping(target = "restaurantId", source="product.restaurant.restaurantId")
    @Mapping(target = "name", source="product.name")
    @Mapping(target = "status", source="product.status")
    @Mapping(target = "thumbnail", source="product.thumbnail")
    @Mapping(target = "modifierGroups", ignore = true)
    CartItemResponse toDto(CartItem cartItem);

    CartItem toEntity(CartItemRequest cartItemRequest);

    @Mapping(target ="cartItemId", ignore = true)
    void updateEntity(CartItemPut cartItemPut, @MappingTarget CartItem cartItem);
}

@Mapper
abstract class CartItemDecorator implements CartItemMapper {
    @Qualifier("delegate")
    @Autowired
    private CartItemMapper delegate;
    @Autowired
    private ModifierMapper modifierMapper;
    @Autowired
    private ModifierGroupRepository modifierGroupRepository;
    @Autowired
    private ModifierRepository modifierRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public CartItemResponse toDto(CartItem cartItem) {
        List<ModifierGroupResponse> modifierGroups = this.cartModifierToModifierGroupDto(cartItem.getModifiers());
        CartItemResponse cartItemResponse = delegate.toDto(cartItem);
        cartItemResponse.setModifierGroups(modifierGroups);
        return cartItemResponse;
    }

    @Override
    public CartItem toEntity(CartItemRequest cartItemRequest) {
        CartItem cartItem = delegate.toEntity(cartItemRequest);

        List<CartModifier> modifiers = modifierGroupDtoToCartModifier(cartItem, cartItemRequest.getModifierGroups());
        Product product = productRepository.findById(cartItemRequest.getProductId()).get();
        cartItem.setProduct(product);
        cartItem.setModifiers(modifiers);
        return cartItem;
    }

    @Override
    public void updateEntity(CartItemPut cartItemPut, CartItem cartItem) {

    }

    private List<CartModifier> modifierGroupDtoToCartModifier(CartItem cartItem, List<ModifierGroupCartRequest> modifierGroupCarts) {
        return modifierGroupCarts.stream().map(modifierGroupCart -> {
            ModifierGroup modifierGroup = modifierGroupRepository.findById(modifierGroupCart.getModifierGroupId()).get();
            List<Modifier> modifiers = modifierRepository.findAllById(modifierGroupCart.getModifiers());

            return modifiers.stream().map(modifier -> {
                CartModifier cartModifier = new CartModifier();
                cartModifier.setCartItem(cartItem);
                cartModifier.setModifierGroup(modifierGroup);
                cartModifier.setModifier(modifier);
                return cartModifier;
            }).toList();
        }).flatMap(Collection::stream).toList();
    }

    private List<ModifierGroupResponse> cartModifierToModifierGroupDto(List<CartModifier> cartModifiers) {
        Map<Integer, ModifierGroupResponse> modifierGroupMp = new HashMap<>();

        cartModifiers.forEach(cartModifier -> {
            Integer modifierGroupId = cartModifier.getModifierGroup().getModifierGroupId();
            modifierGroupMp.putIfAbsent(modifierGroupId, new ModifierGroupResponse());
            modifierGroupMp.get(modifierGroupId).addModifier(modifierMapper.toDto(cartModifier.getModifier()));
        });

        return new ArrayList<>(modifierGroupMp.values());
    }
}
















