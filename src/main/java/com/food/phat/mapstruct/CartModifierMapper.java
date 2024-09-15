package com.food.phat.mapstruct;

import com.food.phat.dto.modifier.ModifierGet;
import com.food.phat.dto.modifier.ModifierGroupGet;
import com.food.phat.dto.modifier.ModifierGroupResponse;
import com.food.phat.entity.CartItem;
import com.food.phat.entity.CartModifier;
import com.food.phat.entity.Modifier;
import com.food.phat.entity.ModifierGroup;
import com.food.phat.repository.ModifierGroupRepository;
import com.food.phat.repository.ModifierRepository;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@DecoratedWith(CartModifierDecorator.class)
public interface CartModifierMapper {
    ArrayList<CartModifier> toEntity(CartItem cartItem, List<ModifierGroupGet> modifierGroups);
    ArrayList<ModifierGroupResponse> toDto(List<CartModifier> cartModifiers);
}

@Mapper
abstract class CartModifierDecorator implements CartModifierMapper {

    @Qualifier("delegate")
    @Autowired
    private CartModifierMapper delegate;
    @Autowired
    private ModifierMapper modifierMapper;
    @Autowired
    private ModifierGroupMapper modifierGroupMapper;
    @Autowired
    private ModifierGroupRepository modifierGroupRepository;
    @Autowired
    private ModifierRepository modifierRepository;

    @Override
    public ArrayList<CartModifier> toEntity(CartItem cartItem, List<ModifierGroupGet> modifierGroups) {
        return modifierGroups.stream().map(modifierGroupCart -> {

            ModifierGroup modifierGroup = modifierGroupRepository.findById(modifierGroupCart.getModifierGroupId()).get();
            List<Modifier> modifiers = modifierRepository.findAllById(
                    modifierGroupCart.getModifiers()
                            .stream()
                            .map(ModifierGet::getModifierId).toList());

            return modifiers.stream().map(modifier -> {

                CartModifier.CartModifierId cartModifierId = new CartModifier.CartModifierId();
                cartModifierId.setModifierId(modifier.getModifierId());
                cartModifierId.setModifierGroupId(modifierGroup.getModifierGroupId());
                cartModifierId.setCartItemId(cartItem.getCartItemId());

                CartModifier cartModifier = new CartModifier();

                cartModifier.setCartModifierId(cartModifierId);
                cartModifier.setCartItem(cartItem);
                cartModifier.setModifierGroup(modifierGroup);
                cartModifier.setModifier(modifier);
                return cartModifier;
            }).toList();
        }).flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<ModifierGroupResponse> toDto(List<CartModifier> cartModifiers) {
        Map<Integer, ModifierGroupResponse> modifierGroupMp = new HashMap<>();

        cartModifiers.forEach(cartModifier -> {
            Integer modifierGroupId = cartModifier.getModifierGroup().getModifierGroupId();

            boolean isExisted = modifierGroupMp.containsKey(modifierGroupId);
            modifierGroupMp.putIfAbsent(modifierGroupId, modifierGroupMapper.toDto(cartModifier.getModifierGroup()));

            if(!isExisted) modifierGroupMp.get(modifierGroupId).getModifiers().clear();
            modifierGroupMp.get(modifierGroupId).addModifier(modifierMapper.toDto(cartModifier.getModifier()));
        });

        return new ArrayList<>(modifierGroupMp.values());
    }
}





















