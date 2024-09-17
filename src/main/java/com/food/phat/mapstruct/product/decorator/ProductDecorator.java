package com.food.phat.mapstruct.product.decorator;

import com.food.phat.dto.product.ProductReponse;
import com.food.phat.dto.product.ProductRequest;
import com.food.phat.entity.Product;
import com.food.phat.mapstruct.product.ProductMapper;
import com.food.phat.repository.MenuCategoryRepository;
import com.food.phat.repository.ModifierGroupRepository;
import com.food.phat.repository.ProductCategoryRepository;
import com.food.phat.repository.RestaurantRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public abstract class ProductDecorator implements ProductMapper {

    @Qualifier("delegate")
    @Autowired
    private ProductMapper delegate;
    @Autowired
    private MenuCategoryRepository menuCategoryRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ModifierGroupRepository modifierGroupRepository;

    @Override
    public void updateEntity(ProductRequest productRequest, Product product) {
        delegate.updateEntity(productRequest, product);

        product.setMenuCategory(menuCategoryRepository.findById(productRequest.getMenuCategoryId()).orElse(null));
        product.setProductCategory(productCategoryRepository.findById(productRequest.getProductCategoryId()).orElse(null));
        product.setRestaurant(restaurantRepository.findById(productRequest.getRestaurantId()).orElse(null));

        if(productRequest.getModifierGroup() != null)
            product.setModifierGroups(modifierGroupRepository.findAllById(productRequest.getModifierGroup()));
    }
}
