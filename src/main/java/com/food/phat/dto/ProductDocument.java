package com.food.phat.dto;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "product_v001")
@Data
public class ProductDocument {

    @Id
    private Integer productId;
    private String name;
    private Boolean status;
    private String description;
    private Float price;
    private String thumbnail;
    private Integer restaurantId;
    private List<Integer> modifierGroupIds;
    private Integer productCategoryId;
    private List<Integer> menuCategoryIds;
}
