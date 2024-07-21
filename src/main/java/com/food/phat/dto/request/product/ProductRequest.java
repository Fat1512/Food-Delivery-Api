package com.food.phat.dto.request.product;


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
    List<Integer> modifierIdList;
    private int categoryId;
    private int restaurantId;
}
