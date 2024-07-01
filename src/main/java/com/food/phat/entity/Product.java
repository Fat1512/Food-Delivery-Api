package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="product")
@Entity
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @Column(name="name")
    private String name;

    @Column(name="status")
    private Boolean status;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private float price;


    public Product() {

    }
}
