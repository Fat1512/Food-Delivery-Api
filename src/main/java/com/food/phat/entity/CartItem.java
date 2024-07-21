package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.List;

@Table(name = "cart_item")
@Entity @DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_item_id")
    private Integer cartItemId;

    @Column(name="qty")
    private int qty;

    @Column(name="note")
    private String note;

    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name="product_fkey")
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="cart_fkey")
    private Cart cart;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "cart_modifier",
            joinColumns = {@JoinColumn(name = "cart_item_fkey")},
            inverseJoinColumns = {@JoinColumn(name = "modifier_option_fkey")})
    private List<ModifierOption> modifierOptions;
}










