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
    @Column(name="cart_id")
    private Integer cartId;

    @OneToMany
    @JoinColumn(name="cart_fkey")
    private List<CartDetail> cartDetail;

    @JoinColumn(name="user_fkey", referencedColumnName = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
