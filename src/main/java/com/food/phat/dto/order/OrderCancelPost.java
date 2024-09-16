package com.food.phat.dto.order;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class OrderCancelPost {
    private Integer orderId;
    private String reason;
}
