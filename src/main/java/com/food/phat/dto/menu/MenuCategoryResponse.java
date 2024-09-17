package com.food.phat.dto.menu;

import com.food.phat.dto.product.ProductReponse;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MenuCategoryResponse {
    private int menuCategoryId;
    private String name;
    private List<ProductReponse> products;
}
