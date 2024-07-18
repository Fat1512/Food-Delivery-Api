package com.food.phat.dto;

import com.food.phat.entity.Category;
import com.food.phat.entity.ModifierOption;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDetailResponse {
    private Integer productId;
    private String name;
    private Boolean status;
    private String description;
    private float price;
    private int qty;
    private String note;
    private String thumbnail;
    List<ModifierOption> modifiers;
    private Category category;
}
