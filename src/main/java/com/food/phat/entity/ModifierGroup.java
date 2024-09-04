package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity @DynamicUpdate
@Table(name="modifier_group")
@Setter
@Getter
public class ModifierGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="modifier_group_id")
    private int modifierGroupId;

    @Column(name="title")
    private String title;

    @OneToMany
    @JoinColumn(name="modifier_group_fkey", referencedColumnName = "modifierGroupId")
    private List<Modifier> modifier;
}
