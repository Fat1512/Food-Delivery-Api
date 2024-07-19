package com.food.phat.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class CartDetailRequest {
    private Integer cartDetailId;

    private List<Integer> modifierOptionsId;

    private int qty;

    private String note;
}
