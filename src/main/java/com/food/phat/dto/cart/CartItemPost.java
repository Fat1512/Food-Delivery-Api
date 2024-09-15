package com.food.phat.dto.cart;

import com.food.phat.dto.modifier.ModifierGroupGet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CartItemPost {
    private Integer productId;
    private int qty;
    private String note;
    private List<ModifierGroupGet> modifierGroups;
}



















