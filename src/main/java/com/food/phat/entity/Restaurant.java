package com.food.phat.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity @DynamicUpdate
@Table(name="restaurant")
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="restaurant_id")
    private Integer restaurantId;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="avatar_image")
    private String avatarImage;

    @Column(name="background_image")
    private String backgroundImage;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @OneToOne
    @JoinColumn(name = "user_fkey")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<Menu> menus;


    @Override
    public int hashCode() {
        return this.restaurantId;
    }

    @Override
    public boolean equals(Object obj) {
        Restaurant res = (Restaurant) obj;
        return res.getRestaurantId().equals(this.restaurantId);
    }

    public void addMemu(Menu menu) {
        this.menus.add(menu);
        menu.setRestaurant(this);
    }
}
