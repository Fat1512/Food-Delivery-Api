package com.food.phat.mapstruct.modifier;

import com.food.phat.dto.modifier.ModifierGroupResponse;
import com.food.phat.entity.ModifierGroup;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ModifierMapper.class)
public interface ModifierGroupMapper {
    ModifierGroupResponse toDto(ModifierGroup modifierGroup);
}

