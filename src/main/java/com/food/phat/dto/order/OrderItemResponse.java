package com.food.phat.dto.order;

import com.food.phat.dto.modifier.ModifierGroupResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class OrderItemResponse {
    private Integer orderItemId;//
    private int qty;//
    private double price;//tay
    private String note;//
    private String thumbnail;//tay
    private String name;//tay
    private Integer productId;//tay
    private Integer restaurantId;//tay
    private Boolean status;//tay
    List<ModifierGroupResponse> modifierGroups;
}
