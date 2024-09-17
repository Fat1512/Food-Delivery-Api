package com.food.phat.mapstruct.product;

import com.food.phat.dto.product.ProductCategoryResponse;
import com.food.phat.entity.ProductCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {
    ProductCategoryResponse toDto(ProductCategory productCategory);
}
