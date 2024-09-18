package com.food.phat.mapstruct.menu.decorator;

import com.food.phat.dto.menu.MenuCategoryRequest;
import com.food.phat.entity.Menu;
import com.food.phat.entity.MenuCategory;
import com.food.phat.entity.Product;
import com.food.phat.mapstruct.menu.MenuCategoryMapper;
import com.food.phat.repository.MenuRepository;
import com.food.phat.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper
public abstract class MenuCategoryDecorator implements MenuCategoryMapper {

    @Qualifier("delegate")
    @Autowired
    private MenuCategoryMapper delegate;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public MenuCategory toEntity(MenuCategoryRequest menuCategoryRequest) {

        MenuCategory menuCategory = delegate.toEntity(menuCategoryRequest);
        menuCategoryRequest.getProductIds().forEach(productId -> {
            Product product = productRepository.findById(productId).get();
            menuCategory.addProduct(product);
        });

        return menuCategory;
    }

    @Override
    public void updateEntity(MenuCategoryRequest menuCategoryRequest, MenuCategory menuCategory) {
        List<Menu> menus = menuRepository.findAllById(menuCategoryRequest.getMenuIds());
        List<Product> products = productRepository.findAllById(menuCategoryRequest.getProductIds());

        menuCategory
    }
}
