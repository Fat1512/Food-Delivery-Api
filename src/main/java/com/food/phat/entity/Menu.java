package com.food.phat.entity;

import jakarta.annotation.Nullable;
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

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="restaurant_fkey")
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(
            name="menu_has_category",
            joinColumns = @JoinColumn(name="menu_fkey"),
            inverseJoinColumns = @JoinColumn(name="menu_category_fkey")
    )
    private List<MenuCategory> menuCategories;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="menu_fkey", nullable = false)
    private List<SellingTime> sellingTimes;

    public void updateMenuCategory(List<MenuCategory>)
}














