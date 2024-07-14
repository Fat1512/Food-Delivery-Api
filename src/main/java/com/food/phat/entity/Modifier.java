package com.food.phat.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="modifier")
public class Modifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="modifier_id")
    private int modifierId;

    @Column(name="title")
    private String title;

    @OneToMany
    @JoinColumn(name="modifier_fkey")
    private List<ModifierOption> modifierOption;
}
