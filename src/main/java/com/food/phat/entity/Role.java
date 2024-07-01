package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name="Role")
@Entity
@Setter
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Role() {

    }
    public Role(String name) {
        this.name = name;
    }
}
