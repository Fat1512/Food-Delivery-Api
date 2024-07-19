package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity @DynamicUpdate
@Table(name="modifier")
@Setter
@Getter
public class Modifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="modifier_id")
    private int modifierId;

    @Column(name="title")
    private String title;

    @OneToMany(mappedBy = "modifier")
    private List<ModifierOption> modifierOption;
}
