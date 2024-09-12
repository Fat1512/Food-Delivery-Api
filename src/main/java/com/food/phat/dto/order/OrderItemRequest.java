package com.food.phat.dto.order;


import lombok.Data;

import java.util.List;

@Data
public class OrderItemRequest {
    private Integer productId;
    private Integer qty;
    private Float price;
    private String note;
    private List<Integer> modifierOptionsId;
}
