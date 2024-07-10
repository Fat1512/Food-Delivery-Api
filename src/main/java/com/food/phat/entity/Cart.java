package com.food.phat.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name="cart")
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @ManyToMany
    @JoinTable(
            name="cart_detail",
            joinColumns = @JoinColumn(name="cart_pkey"),
            inverseJoinColumns = @JoinColumn(name="product_pkey")
    )
    private List<Product> products;

    @JoinColumn(name="user_pkey", referencedColumnName = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
