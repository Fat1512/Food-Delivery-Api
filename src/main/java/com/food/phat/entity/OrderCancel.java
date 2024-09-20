
package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="order_cancel")
@Setter
@Getter
public class OrderCancel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer orderId;

    @Column(name="reason")
    private String reason;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name="order_id")
    private Order order;
}























