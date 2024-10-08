package com.food.phat.dto.cart;


import com.food.phat.dto.modifier.ModifierGroupGet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CartItemPut {
    private int cartItemId;
    private int qty;
    private List<ModifierGroupGet> modifierGroups;
}
