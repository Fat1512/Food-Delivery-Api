package com.food.phat.mapstruct;

import com.food.phat.dto.ModifierGroupDTO;
import com.food.phat.entity.ModifierGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ModifierMapper.class)
public interface ModifierGroupMapper {
    ModifierGroupDTO toDto(ModifierGroup modifierGroup);
}
