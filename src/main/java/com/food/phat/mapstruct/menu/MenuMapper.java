package com.food.phat.mapstruct.menu;

import com.food.phat.dto.menu.MenuRequest;
import com.food.phat.dto.menu.MenuResponse;
import com.food.phat.entity.Menu;
import com.food.phat.mapstruct.menu.decorator.MenuDecorator;
import com.food.phat.mapstruct.sellingtime.SellingTimeMapper;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MenuCategoryMapper.class, SellingTimeMapper.class})
@DecoratedWith(MenuDecorator.class)
public interface MenuMapper {
    MenuResponse toDto(Menu menu);
    Menu toEntity(MenuRequest menuRequest);
    @Mapping(target="menuId", ignore = true)
    void updateEntity(MenuRequest menuRequest, @MappingTarget Menu menu);
}
