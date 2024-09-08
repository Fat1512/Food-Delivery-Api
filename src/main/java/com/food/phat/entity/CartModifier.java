package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Table(name="cart_modifier")
@Entity @DynamicUpdate
@Setter
@Getter
public class CartModifier {

    @JoinColumn(name = "cart_item_fkey")
    @ManyToOne(optional = false)
    @Id
    private CartItem cartItem;

    @JoinColumn(name = "modifier_fkey", referencedColumnName = "modifier_id")
    @ManyToOne(optional = false)
    @Id
    private Modifier modifier;
}
