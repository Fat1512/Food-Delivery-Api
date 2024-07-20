package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name="order_tb")
@Entity
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer orderId;

    @Column(name="shipping_fee")
    private float shippingFee;

    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name="customer_address_fkey")
    private CustomerAddress customerAddress;

    @ManyToOne
    @JoinColumn(name="restaurant_fkey")
    private Restaurant restaurant;

    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.LAZY)
    private List<OrderItem> orderItem;

    public void addOrderItem(OrderItem orderItem) {
        if(this.orderItem.isEmpty()) this.orderItem = new ArrayList<>();
        this.orderItem.add(orderItem);
        orderItem.setOrder(this);
    }
}
