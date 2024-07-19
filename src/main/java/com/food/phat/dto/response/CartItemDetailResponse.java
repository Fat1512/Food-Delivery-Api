package com.food.phat.dto.response;

import com.food.phat.entity.Category;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemDetailResponse {
    private Integer productId;
    private String name;
    private Boolean status;
    private String description;
    private float price;
    private int qty;
    private String note;
    private String thumbnail;
    List<Object[]> modifiers;
    private Category category;
}
