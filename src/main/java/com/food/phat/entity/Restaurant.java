package com.food.phat.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
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
}
