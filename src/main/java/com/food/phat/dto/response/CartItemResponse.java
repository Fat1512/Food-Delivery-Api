package com.food.phat.dto.response;

import com.food.phat.dto.ModifierGroupDTO;
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
    private int qty;
    private String thumbnail;
    List<ModifierGroupDTO> modifierGroups;
}
