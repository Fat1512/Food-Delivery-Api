package com.food.phat.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Integer productId;
    private String name;
    private Boolean status;
    private String description;
    private float price;
    private String thumbnail;
    private Integer restaurantId;
    List<ModifierGroupDTO> modifierGroups;
}

//Doesn't have qty, note when compared to CartItemResponse