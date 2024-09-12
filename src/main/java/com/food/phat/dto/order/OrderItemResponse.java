package com.food.phat.dto.order;

import com.food.phat.dto.modifier.ModifierGroupDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class OrderItemResponse {
    private Integer orderItemId;//
    private Integer productId;
    private Integer restaurantId;
    private String name;
    private Boolean status;
    private int qty;//
    private String thumbnail;
    private double price;
    private String note;
    List<ModifierGroupDTO> modifierGroups;
}
