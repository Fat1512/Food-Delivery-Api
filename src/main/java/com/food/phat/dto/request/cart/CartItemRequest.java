package com.food.phat.dto.request.cart;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CartItemRequest {
    private Integer cartItemId;
    private Integer productId;
    private List<Integer> modifierOptionsId;
    private int qty;
    private String note;
}
