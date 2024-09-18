package com.food.phat.service;

import com.food.phat.dto.menu.MenuPost;
import com.food.phat.dto.menu.MenuResponse;

import java.util.List;

public interface MenuService {
    MenuResponse getMenu(Integer menuId);
    List<MenuResponse> getMenus(Integer restaurantId);
    void deleteMenu(Integer menuId);
    void createMenu(Integer restaurantId, MenuPost menuPost);
}
