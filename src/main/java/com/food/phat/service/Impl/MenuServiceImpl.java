package com.food.phat.service.Impl;

import com.food.phat.dto.menu.MenuPost;
import com.food.phat.dto.menu.MenuResponse;
import com.food.phat.entity.Menu;
import com.food.phat.entity.Restaurant;
import com.food.phat.mapstruct.menu.MenuMapper;
import com.food.phat.repository.MenuCategoryRepository;
import com.food.phat.repository.MenuRepository;
import com.food.phat.repository.RestaurantRepository;
import com.food.phat.service.MenuService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(
            MenuRepository menuRepository,
            MenuMapper menuMapper,
            RestaurantRepository restaurantRepository, MenuCategoryRepository menuCategoryRepository) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional
    public MenuResponse getMenu(Integer restaurantId, Integer menuId) {
        Menu menu = menuRepository.findByRestauranIdAndMenuId(restaurantId, menuId);
        return menuMapper.toDto(menu);
    }

    @Override
    public List<MenuResponse> getMenus(Integer restaurantId) {
        List<Menu> menus = menuRepository.findAllByRestaurantId(restaurantId);
        List<MenuResponse> menuResponses = menus.stream().map(menuMapper::toDto).toList();
        return menuResponses;
    }

    @Override
    @Transactional
    public void deleteMenu(Integer restaurantId, Integer menuId) {
        menuRepository.deleteByRestaurantIdAndMenuId(restaurantId, menuId);
    }

    @Override
    @Transactional
    public void createMenu(Integer restaurantId, MenuPost menuPost) {
        Menu menu = menuMapper.toEntity(menuPost);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        restaurant.addMemu(menu);
        menuRepository.save(menu);
    }
}
