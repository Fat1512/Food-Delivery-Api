package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Table(name="cart_modifier")
@Entity
@DynamicUpdate
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartModifier {

    @EmbeddedId
    private CartModifierId cartModifierId;

    public CartModifier(CartItem cartItem, ModifierGroup modifierGroup, Modifier modifier) {
        this.cartModifierId = new CartModifierId(
                cartItem.getCartItemId(),
                modifierGroup.getModifierGroupId(),
                modifier.getModifierId());
        this.cartItem = cartItem;
        this.modifierGroup = modifierGroup;
        this.modifier = modifier;
    }

    @JoinColumn(name = "cart_item_fkey")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("cartItemId")
    private CartItem cartItem;

    @JoinColumn(name = "modifier_group_fkey", referencedColumnName = "modifier_group_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("modifierGroupId")
    private ModifierGroup modifierGroup;

    @JoinColumn(name = "modifier_fkey", referencedColumnName = "modifier_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("modifierId")
    private Modifier modifier;

    @Embeddable
    @Setter
    @Getter
    @AllArgsConstructor
    public static class CartModifierId implements Serializable {
        @Column(name="cart_item_fkey")
        private Integer cartItemId;

        @Column(name="modifier_group_fkey")
        private Integer modifierGroupId;

        @Column(name="modifier_fkey")
        private Integer modifierId;

        public CartModifierId() {

        }
    }
}
