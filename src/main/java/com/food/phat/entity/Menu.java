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

    @Column(name="name")
    private String name;

    @OneToOne
    @JoinColumn(name="restaurant_fkey")
    private Restaurant restaurant;

    @OneToMany
    @JoinColumn(name="menu_fkey")
    private List<MenuCategory> menuCategory;

    @ManyToMany
    @JoinTable(
            name="menu_selling_time",
            joinColumns = @JoinColumn(name="menu_fkey"),
            inverseJoinColumns = @JoinColumn(name="selling_time_fkey")
    )
    private List<SellingTime> sellingTimes;
}
