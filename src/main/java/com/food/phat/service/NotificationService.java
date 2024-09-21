package com.food.phat.service;

import java.util.List;

public interface NotificationService {
    void send(String subject, String content, String ...to);

    List<String> getSubscriptedEmail(Integer restaurantId);

}
