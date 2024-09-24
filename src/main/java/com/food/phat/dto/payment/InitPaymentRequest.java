package com.food.phat.dto.payment;


import lombok.Data;

@Data
public class InitPaymentRequest {
    private Integer txnRef; //orderId
    private Integer amount; //totalOrderIdAmount
    private String ipAddress; //ipaddress
}
