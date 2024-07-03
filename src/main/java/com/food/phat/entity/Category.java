package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name="category")
@Entity
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Integer CategoryId;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;

    public Category() {

    }
}
