package com.food.phat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Table(name = "cart_detail")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_detail_id")
    private Integer cartDetailId;

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
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_fkey")
    private Product product;

    @ManyToMany
    @JoinTable(
            name = "cart_modifier",
            joinColumns = @JoinColumn(name="cart_detail_fkey"),
            inverseJoinColumns = @JoinColumn(name="modifier_option_fkey"))
    List<ModifierOption> modifierOptions;

}
