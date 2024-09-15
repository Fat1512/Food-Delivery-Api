package com.food.phat.dto.product;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ProductRequest {
    private Integer productId;
    private String name;
    private Boolean status;
    private String description;
    private float price;
    private String thumbnail;

    private int productCategoryId;
    private int menuCategoryId;
    private int restaurantId;
    List<Integer> modifierGroup;
}
