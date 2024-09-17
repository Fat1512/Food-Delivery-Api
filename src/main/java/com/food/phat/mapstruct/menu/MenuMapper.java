package com.food.phat.mapstruct.menu;

import com.food.phat.dto.menu.MenuPost;
import com.food.phat.dto.menu.MenuResponse;
import com.food.phat.entity.Menu;
import com.food.phat.mapstruct.sellingtime.SellingTimeMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MenuCategoryMapper.class, SellingTimeMapper.class})
public interface MenuMapper {
    MenuResponse toDto(Menu menu);
    Menu toEntity(MenuPost menuPost);
}
