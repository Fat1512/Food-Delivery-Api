package com.food.phat.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItem;

    @JoinColumn(name="user_fkey", referencedColumnName = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public void addCartItem(CartItem cartItem) {
        if(this.cartItem.isEmpty()) this.cartItem = new ArrayList<>();
        this.cartItem.add(cartItem);
        cartItem.setCart(this);
    }

}
