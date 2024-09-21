package com.food.phat.service;

import com.food.phat.dto.NotificationDetailResponse;

import java.util.List;

public interface NotificationService {
    void send(String subject, String content, String ...to);

    NotificationDetailResponse getSubscriptionDetail(Integer restaurantId);

}
