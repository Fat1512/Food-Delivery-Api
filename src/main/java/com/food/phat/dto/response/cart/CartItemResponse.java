package com.food.phat.dto.response.cart;

import com.food.phat.entity.Category;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemResponse {
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
