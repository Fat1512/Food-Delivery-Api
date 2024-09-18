package com.food.phat.mapstruct.menu.decorator;

import com.food.phat.dto.menu.MenuRequest;
import com.food.phat.entity.Menu;
import com.food.phat.entity.MenuCategory;
import com.food.phat.mapstruct.menu.MenuMapper;
import com.food.phat.repository.MenuCategoryRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper
public abstract class MenuDecorator implements MenuMapper {

    @Qualifier("delegate")
    @Autowired
    private MenuMapper delegate;

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Override
    public Menu toEntity(MenuRequest menuRequest) {
        Menu menu = delegate.toEntity(menuRequest);
        List<MenuCategory> menuCategories = menuCategoryRepository.findAllById(menuRequest.getMenuCategoryIds());
        menu.setMenuCategories(menuCategories);
        return menu;
    }

    @Override
    public void updateEntity(MenuRequest menuRequest, Menu menu) {
        delegate.updateEntity(menuRequest, menu);
        List<MenuCategory> menuCategories = menuCategoryRepository.findAllById(menuRequest.getMenuCategoryIds());
        menu.setMenuCategories(menuCategories);
    }
}
