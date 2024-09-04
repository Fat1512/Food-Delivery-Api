package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity @DynamicUpdate
@Table(name="menu")
@Setter
@Getter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menu_id")
    private Integer menuId;

    @Column(name="menu")
    private String name;

    @OneToOne
    @JoinColumn(name="restaurant_fkey")
    private Restaurant restaurant;

    @OneToMany
    @JoinColumn(name="menu_fkey")
    private List<MenuCategory> menuCategory;
}
