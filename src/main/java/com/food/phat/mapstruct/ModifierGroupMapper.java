package com.food.phat.mapstruct;

import com.food.phat.dto.ModifierGroupDTO;
import com.food.phat.entity.ModifierGroup;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring", uses = ModifierMapper.class)
@DecoratedWith(ModifierGroupDecorator.class)
public interface ModifierGroupMapper {
    ModifierGroupDTO toDto(ModifierGroup modifierGroup);
}

@Mapper
abstract class ModifierGroupDecorator implements ModifierGroupMapper {
    @Qualifier("delegate")
    @Autowired
    private ModifierGroupMapper delegate;

    private final ModifierMapper modifierMapper = Mappers.getMapper(ModifierMapper.class);

    @Override
    public ModifierGroupDTO toDto(ModifierGroup modifierGroup) {
        ModifierGroupDTO modifierGroupDTO = delegate.toDto(modifierGroup);
        modifierGroupDTO.setModifiers(modifierGroup.getModifier().stream().map(modifierMapper::toDto).toList());
        return modifierGroupDTO;
    }
}