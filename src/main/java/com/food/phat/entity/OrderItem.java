package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Table(name="order_item")
@Entity
@Setter
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Integer orderItemId;

    @Column(name="qty")
    private int qty;

    @Column(name="price")
    private float price;

    @Column(name="note")
    private String note;

    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name="product_fkey")
    private Product product;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "order_modifier",
            joinColumns = {@JoinColumn(name = "order_item_fkey")},
            inverseJoinColumns = {@JoinColumn(name = "modifier_option_fkey")})
    private List<Modifier> modifiers;

    @ManyToOne
    @JoinColumn(name="order_fkey")
    private Order order;
}
