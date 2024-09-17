package com.food.phat.mapstruct.order;


import com.food.phat.dto.modifier.ModifierGroupGet;
import com.food.phat.dto.modifier.ModifierGroupResponse;
import com.food.phat.entity.*;
import com.food.phat.mapstruct.order.decorator.OrderModifierDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import java.util.*;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderModifierDecorator.class)
public interface OrderModifierMapper {
    ArrayList<OrderModifier> toEntity(OrderItem orderItem, List<ModifierGroupGet> modifierGroups);
    ArrayList<ModifierGroupResponse> toDto(List<OrderModifier> orderModifiers);
}













