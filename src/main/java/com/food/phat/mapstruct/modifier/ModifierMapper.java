package com.food.phat.mapstruct.modifier;


import com.food.phat.dto.modifier.ModifierResponse;
import com.food.phat.entity.Modifier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModifierMapper {
    ModifierResponse toDto(Modifier modifier);
}
