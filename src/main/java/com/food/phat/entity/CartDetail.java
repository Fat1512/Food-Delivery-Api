package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.List;

@Table(name = "cart_detail")
@Entity @DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_detail_id")
    private Integer cartDetailId;

    @ManyToOne
    @JoinColumn(name="product_fkey")
    private Product product;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "cart_modifier",
            joinColumns = {@JoinColumn(name = "cart_detail_fkey")},
            inverseJoinColumns = {@JoinColumn(name = "modifier_option_fkey")})
    private List<ModifierOption> modifierOptions;

    @Column(name="qty")
    private int qty;

    @Column(name="price")
    private float price;

    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(name="note")
    private String note;

    @ManyToOne
    @JoinColumn(name="cart_fkey")
    private Cart cart;
}
