
package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="order_cancel")
@Setter
@Getter
public class OrderCancel extends Order {
    private String reason;
}
