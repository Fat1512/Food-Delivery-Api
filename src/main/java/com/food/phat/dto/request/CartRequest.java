package com.food.phat.dto.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CartRequest {
    private Integer cartId;
    private Integer cartItemId;
    private Integer productId;
    private int qty;
    private String note;
    private List<Integer> modifierOptionsId;
}
