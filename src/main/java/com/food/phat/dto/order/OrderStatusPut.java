package com.food.phat.dto.order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class OrderStatusPut {
    private int orderId;
    private String status;
}
