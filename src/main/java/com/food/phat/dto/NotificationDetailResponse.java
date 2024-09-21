package com.food.phat.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotificationDetailResponse {
    private String objectName;
    private List<String> emailList;
}
