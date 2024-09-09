package com.food.phat.dto.response;

import com.food.phat.dto.ModifierGroupDTO;
import com.food.phat.dto.ProductCategoryDTO;
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
    private List<ModifierGroupDTO> modifierGroups;
    private List<ProductCategoryDTO> productCategories;
}

//Doesn't have qty, note when compared to CartItemResponse