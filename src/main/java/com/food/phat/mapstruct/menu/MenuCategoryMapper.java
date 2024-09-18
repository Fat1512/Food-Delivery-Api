package com.food.phat.mapstruct.menu;

import com.food.phat.dto.menu.MenuCategoryRequest;
import com.food.phat.dto.menu.MenuCategoryResponse;
import com.food.phat.entity.MenuCategory;
import com.food.phat.mapstruct.menu.decorator.MenuCategoryDecorator;
import com.food.phat.mapstruct.product.ProductMapper;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
@DecoratedWith(MenuCategoryDecorator.class)
public interface MenuCategoryMapper {
    MenuCategoryResponse toDto(MenuCategory menuCategory);
    MenuCategory toEntity(MenuCategoryRequest menuCategoryRequest);
    void updateEntity(MenuCategoryRequest menuCategoryRequest, @MappingTarget MenuCategory menuCategory);
}


