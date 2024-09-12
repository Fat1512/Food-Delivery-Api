package com.food.phat.mapstruct;


import com.food.phat.dto.modifier.ModifierDTO;
import com.food.phat.entity.Modifier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModifierMapper {
    ModifierDTO toDto(Modifier modifier);
}
