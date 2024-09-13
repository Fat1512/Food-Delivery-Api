package com.food.phat.dto.order;


import lombok.Data;

@Data
public class OrderItemPost {
    private int cartItemId;
    private String note;
}
