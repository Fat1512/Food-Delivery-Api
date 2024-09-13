package com.food.phat.mapstruct;


import com.food.phat.dto.modifier.ModifierGroupResponse;
import com.food.phat.entity.*;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderModifierDecorator.class)
public interface OrderModifierMapper {
//    List<OrderModifier> toEntity(OrderItem cartItem, List<ModifierGroupGet> modifierGroupCarts);
    List<ModifierGroupResponse> toDto(List<OrderModifier> orderModifiers);
}


@Mapper
abstract class OrderModifierDecorator implements OrderModifierMapper {

    @Qualifier("delegate")
    @Autowired
    private OrderModifierMapper delegate;
    @Autowired
    private ModifierMapper modifierMapper;
    @Autowired
    private ModifierGroupMapper modifierGroupMapper;

    @Override
    public List<ModifierGroupResponse> toDto(List<OrderModifier> orderModifiers) {
        Map<Integer, ModifierGroupResponse> modifierGroupMp = new HashMap<>();

        orderModifiers.forEach(cartModifier -> {
            Integer modifierGroupId = cartModifier.getModifierGroup().getModifierGroupId();

            boolean isExisted = true;
            if(!modifierGroupMp.containsKey(modifierGroupId)) isExisted = false;
            modifierGroupMp.putIfAbsent(modifierGroupId, modifierGroupMapper.toDto(cartModifier.getModifierGroup()));

            if(!isExisted) modifierGroupMp.get(modifierGroupId).getModifiers().clear();
            modifierGroupMp.get(modifierGroupId).addModifier(modifierMapper.toDto(cartModifier.getModifier()));
        });

        return new ArrayList<>(modifierGroupMp.values());
    }
}