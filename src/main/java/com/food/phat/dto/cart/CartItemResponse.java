package com.food.phat.dto.cart;

import com.food.phat.dto.modifier.ModifierGroupResponse;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemResponse {
    private Integer cartItemId;//
    private Integer productId;
    private Integer restaurantId;
    private String name;
    private Boolean status;
    private int qty;//
    private String thumbnail;
    List<ModifierGroupResponse> modifierGroups;
}




























