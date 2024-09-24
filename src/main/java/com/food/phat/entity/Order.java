package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name="order_tb")
@Entity
@Setter
@Getter
public class Order  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer orderId;

    @Column(name="shipping_fee")
    private Integer shippingFee;

    @Column(name="note")
    private String note;

    @Column(name="submit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitTime;

    @Column(name="complete_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completeTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_address_fkey")
    private CustomerAddress customerAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_fkey")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_fkey")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="order_fkey")
    private List<OrderItem> orderItems;

    public void addOrderItem(List<OrderItem> orderItem) {
        orderItems.addAll(orderItem);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }
}







