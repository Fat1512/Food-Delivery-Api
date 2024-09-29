package com.food.phat.mapstruct.product;

import com.food.phat.dto.ProductDocument;
import com.food.phat.dto.product.ProductResponse;
import com.food.phat.dto.product.ProductRequest;
import com.food.phat.entity.Product;
import com.food.phat.mapstruct.modifier.ModifierGroupMapper;
import com.food.phat.mapstruct.product.decorator.ProductDecorator;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ModifierGroupMapper.class, ProductCategoryMapper.class})
@DecoratedWith(ProductDecorator.class)
public interface ProductMapper {
    @Mapping(target = "productId", ignore = true)
    void updateEntity(ProductRequest productRequest, @MappingTarget Product product);

    @Mapping(target = "restaurantId", source = "restaurant.restaurantId")
    ProductResponse toDto(Product product);

    Product toEntity(ProductRequest productRequest);

    ProductDocument toProductDocument(Product product);
}











