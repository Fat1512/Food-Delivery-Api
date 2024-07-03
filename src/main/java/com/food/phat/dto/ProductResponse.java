package com.food.phat.dto;


import com.food.phat.entity.Category;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private Integer productId;
    private String name;
    private Boolean status;
    private String description;
    private float price;
    private String thumbnail;
    private Category category;
}
