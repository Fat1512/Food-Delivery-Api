package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

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

    @Column(name="price")
    private float price;

    @Column(name="status")
    private boolean status;

//    @ManyToOne
//    @JoinColumn(name="modifier_group_fkey")
//    @JsonIgnore
//    private ModifierGroup modifierGroup;
}
