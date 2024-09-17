package com.food.phat.dto.product;

import com.food.phat.dto.modifier.ModifierGroupResponse;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReponse {
    private Integer productId;
    private String name;
    private Boolean status;
    private String description;
    private float price;
    private String thumbnail;
    private Integer restaurantId;
    private List<ModifierGroupResponse> modifierGroups;
    private ProductCategoryResponse productCategory;
}

//Doesn't have qty, note when compared to CartItemResponse