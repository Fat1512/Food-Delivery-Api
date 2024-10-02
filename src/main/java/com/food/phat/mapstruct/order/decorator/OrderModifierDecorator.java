package com.food.phat.mapstruct.order.decorator;

import com.food.phat.dto.modifier.ModifierGet;
import com.food.phat.dto.modifier.ModifierGroupGet;
import com.food.phat.dto.modifier.ModifierGroupResponse;
import com.food.phat.entity.*;
import com.food.phat.mapstruct.modifier.ModifierGroupMapper;
import com.food.phat.mapstruct.modifier.ModifierMapper;
import com.food.phat.mapstruct.order.OrderModifierMapper;
import com.food.phat.repository.ModifierGroupRepository;
import com.food.phat.repository.ModifierRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Mapper
public abstract class OrderModifierDecorator implements OrderModifierMapper {

    @Qualifier("delegate")
    @Autowired
    private OrderModifierMapper delegate;
    @Autowired
    private ModifierMapper modifierMapper;
    @Autowired
    private ModifierGroupMapper modifierGroupMapper;
    @Autowired
    private ModifierGroupRepository modifierGroupRepository;
    @Autowired
    private ModifierRepository modifierRepository;

    @Override
    public ArrayList<OrderModifier> toEntity(OrderItem orderItem, List<ModifierGroupGet> modifierGroups) {
        return modifierGroups.stream().map(modifierGroupCart -> {

            ModifierGroup modifierGroup = modifierGroupRepository.findById(modifierGroupCart.getModifierGroupId()).get();
            List<Modifier> modifiers = modifierRepository.findAllById(
                    modifierGroupCart.getModifiers()
                            .stream()
                            .map(ModifierGet::getModifierId).toList());

            return modifiers.stream().map(modifier -> {

                OrderModifier.OrderModifierId cartModifierId = new OrderModifier.OrderModifierId();
                cartModifierId.setModifierId(modifier.getModifierId());
                cartModifierId.setModifierGroupId(modifierGroup.getModifierGroupId());
                cartModifierId.setOrderItemId(orderItem.getOrderItemId());

                OrderModifier orderModifier = new OrderModifier();

                orderModifier.setOrderModifierid(cartModifierId);
                orderModifier.setOrderItem(orderItem);
                orderModifier.setModifierGroup(modifierGroup);
                orderModifier.setModifier(modifier);
                return orderModifier;
            }).toList();
        }).flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<ModifierGroupResponse> toDto(List<OrderModifier> orderModifiers) {

        Map<ModifierGroup, List<OrderModifier>> groupedOrderModifier = orderModifiers.stream()
                .collect(groupingBy(OrderModifier::getModifierGroup));

        return groupedOrderModifier.entrySet().stream().map(groupedModifier -> ModifierGroupResponse.builder()
                .modifierGroupId(groupedModifier.getKey().getModifierGroupId())
                .title(groupedModifier.getKey().getTitle())
                .modifiers(groupedModifier.getValue().stream()
                        .map(orderModifier -> modifierMapper.toDto(orderModifier.getModifier())).toList())
                .build()).collect(Collectors.toCollection(ArrayList::new));
    }
}


























//Map<Integer, ModifierGroupResponse> modifierGroupMp = new HashMap<>();
//
//        orderModifiers.forEach(cartModifier -> {
//Integer modifierGroupId = cartModifier.getModifierGroup().getModifierGroupId();
//
//boolean isExisted = modifierGroupMp.containsKey(modifierGroupId);
//            modifierGroupMp.putIfAbsent(modifierGroupId, modifierGroupMapper.toDto(cartModifier.getModifierGroup()));
//
//        if(!isExisted) modifierGroupMp.get(modifierGroupId).getModifiers().clear();
//            modifierGroupMp.get(modifierGroupId).addModifier(modifierMapper.toDto(cartModifier.getModifier()));
//        });
//
//        return new ArrayList<>(modifierGroupMp.values());