package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "notification_subscription")
public class NotificationSubscription {

    @EmbeddedId
    private NotificationSubscriptionId notificationSubscriptionId;

    @JoinColumn(name="user_fkey")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @JoinColumn(name="restaurant_fkey")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurantId")
    private Restaurant restaurant;


    @Embeddable
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NotificationSubscriptionId implements Serializable {
        @Column(name="user_fkey")
        private Integer userId;

        @Column(name="restaurant_fkey")
        private Integer restaurantId;

    }
}
