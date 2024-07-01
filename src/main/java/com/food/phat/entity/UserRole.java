package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Table(name="role")
@Entity
@Setter
@Getter
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public UserRole() {

    }

    @JoinColumn(name = "user_pkey", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userPkey;

    @JoinColumn(name = "role_pkey", referencedColumnName = "role_id")
    @ManyToOne(optional = false)
    private Role rolePkey;
}
