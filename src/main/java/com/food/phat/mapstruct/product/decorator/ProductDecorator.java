package com.food.phat.mapstruct.product.decorator;

import com.food.phat.dto.ProductDocument;
import com.food.phat.dto.product.ProductRequest;
import com.food.phat.entity.MenuCategory;
import com.food.phat.entity.ModifierGroup;
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

        product.setMenuCategories(menuCategoryRepository.findAllById(productRequest.getMenuCategoryIds()));
        product.setProductCategory(productCategoryRepository.findById(productRequest.getProductCategoryId()).orElse(null));
        product.setRestaurant(restaurantRepository.findById(productRequest.getRestaurantId()).orElse(null));
        product.setModifierGroups(modifierGroupRepository.findAllById(productRequest.getModifierGroupIds()));
    }

    @Override
    public ProductDocument toProductDocument(Product product) {
        ProductDocument productDocument = delegate.toProductDocument(product);

        productDocument.setRestaurantId(product.getRestaurant().getRestaurantId());
        productDocument.setProductCategoryId(product.getProductCategory().getProductCategoryId());
        productDocument.setModifierGroupIds(product.getModifierGroups().stream().map(ModifierGroup::getModifierGroupId).toList());
        productDocument.setMenuCategoryIds(product.getMenuCategories().stream().map(MenuCategory::getMenuCategoryId).toList());

        return productDocument;
    }

    @Override
    public Product toEntity(ProductRequest productRequest) {
        Product product = delegate.toEntity(productRequest);
        menuCategoryRepository.findAllById(productRequest.getMenuCategoryIds())
                .forEach(menuCategory -> menuCategory.addProduct(product));
        product.setProductCategory(productCategoryRepository.findById(productRequest.getProductCategoryId()).orElse(null));
        product.setRestaurant(restaurantRepository.findById(productRequest.getRestaurantId()).orElse(null));
        product.setModifierGroups(modifierGroupRepository.findAllById(productRequest.getModifierGroupIds()));
        return product;
    }
}
