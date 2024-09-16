
package com.food.phat.entity;

import jakarta.persistence.*;

@Entity
@Table(name="order_cancel")
public class OrderCancel extends Order {
    private String reason;
}
