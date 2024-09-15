package com.food.phat.dto.order;

import com.food.phat.dto.modifier.ModifierGroupGet;
import lombok.Data;

import java.util.List;

@Data
public class OrderItemPost {
    private Integer productId;
    private Integer qty;//
    private Double price;//
    private String note;//
    private List<ModifierGroupGet> modifierGroups;
}























