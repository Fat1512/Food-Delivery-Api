package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity @DynamicUpdate
@Table(name="menu")
@Setter
@Getter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menuId")
    private Integer menuId;

    @Column(name="menu")
    private String name;

//    @ManyToOne
}
