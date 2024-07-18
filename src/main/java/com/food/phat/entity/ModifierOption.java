package com.food.phat.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="modifier_option")
@Setter
@Getter
public class ModifierOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="modifier_option_id")
    private int modifierOptionId;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private float price;

    @Column(name="status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name="modifier_fkey")
    @JsonIgnore
    private Modifier modifier;
}
