package com.food.phat.dto.menu;

import com.food.phat.dto.product.ProductReponse;

import java.util.List;

public class MenuCategoryResponse {
    private int menuCategoryId;
    private String name;
    private List<ProductReponse> products;
}
