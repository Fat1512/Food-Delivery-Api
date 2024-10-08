package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name="customer_address")
@Entity
@Getter
@Setter
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_address_id")
    private Integer customerAddressId;

    @Column(name="address")
    private String address;

    @Column(name="country")
    private String country;

    @Column(name="city")
    private String city;

    @Column(name="phone")
    private String phone;

    @Column(name="note")
    private String note;

    @ManyToOne
    @JoinColumn(name="user_fkey")
    private User user;
}





















