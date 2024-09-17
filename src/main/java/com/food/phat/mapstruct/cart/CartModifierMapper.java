package com.food.phat.mapstruct.cart;

import com.food.phat.dto.modifier.ModifierGroupGet;
import com.food.phat.dto.modifier.ModifierGroupResponse;
import com.food.phat.entity.CartItem;
import com.food.phat.entity.CartModifier;
import com.food.phat.mapstruct.cart.decorator.CartModifierDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import java.util.*;

@Mapper(componentModel = "spring")
@DecoratedWith(CartModifierDecorator.class)
public interface CartModifierMapper {
    ArrayList<CartModifier> toEntity(CartItem cartItem, List<ModifierGroupGet> modifierGroups);
    ArrayList<ModifierGroupResponse> toDto(List<CartModifier> cartModifiers);
}





















