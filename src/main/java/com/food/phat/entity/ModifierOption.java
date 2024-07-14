package com.food.phat.entity;


import jakarta.persistence.*;

@Entity
@Table(name="modifier_option")
public class ModifierOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="modifier_option_id")
    private int modifierOptionId;

    @Column(name="title")
    private String title;

}
