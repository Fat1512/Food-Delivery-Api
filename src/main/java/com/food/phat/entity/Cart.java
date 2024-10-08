package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Setter
@Table(name="cart")
@Entity @DynamicUpdate
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Integer cartId;

    @JoinColumn(name="user_fkey", referencedColumnName = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="cart_fkey", nullable = false)
    private List<CartItem> cartItem;

    public void addCartItem(CartItem cartItem) {
        this.cartItem.add(cartItem);
    }
}





























