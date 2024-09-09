package com.food.phat.mapstruct;

import com.food.phat.dto.ProductReponse;
import com.food.phat.dto.request.product.ProductRequest;
import com.food.phat.entity.Product;
import com.food.phat.repository.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring", uses = ModifierMapper.class)
@DecoratedWith(ProductDecorator.class)
public interface ProductMapper {
    @Mapping(target = "productId", ignore = true)
    void updateEntity(ProductRequest productRequest, @MappingTarget Product product);

    @Mapping(target = "restaurantId", source = "restaurant.restaurantId")
    ProductReponse toDto(Product product);
}

@Mapper
abstract class ProductDecorator implements ProductMapper {

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

    @Override
    public ProductReponse toDto(Product product) {
        ProductReponse productReponse = delegate.toDto(product);
        return productReponse;
    }
}