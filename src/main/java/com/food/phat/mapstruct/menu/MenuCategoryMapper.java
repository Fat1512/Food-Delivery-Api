package com.food.phat.mapstruct.menu;

import com.food.phat.dto.menu.MenuCategoryResponse;
import com.food.phat.entity.MenuCategory;
import com.food.phat.mapstruct.product.ProductMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface MenuCategoryMapper {
    MenuCategoryResponse toDto(MenuCategory menuCategory);
}


