package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Table(name="order_modifier")
@Entity
@DynamicUpdate
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModifier {

    @EmbeddedId
    private OrderModifierId OrderModifierid;

    public OrderModifier(OrderItem orderItem, ModifierGroup modifierGroup, Modifier modifier) {
        OrderModifierid = new OrderModifierId(
                orderItem.getOrderItemId(),
                modifierGroup.getModifierGroupId(),
                modifier.getModifierId());
        this.orderItem = orderItem;
        this.modifierGroup = modifierGroup;
        this.modifier = modifier;
    }

    @JoinColumn(name = "order_item_fkey")
    @ManyToOne(optional = false)
    @MapsId("orderItemId")
    private OrderItem orderItem;

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
    public static class OrderModifierId implements Serializable {
        @Column(name="order_item_fkey")
        private int orderItemId;

        @Column(name="modifier_group_fkey")
        private int modifierGroupId;

        @Column(name="modifier_fkey")
        private int modifierId;

        public OrderModifierId() {

        }
    }
}
