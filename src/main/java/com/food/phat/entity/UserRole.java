package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@Table(name="user_role")
@Entity @DynamicUpdate
@Setter
@Getter
public class UserRole {

    public UserRole() {

    }

    @JoinColumn(name = "user_fkey", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    @Id
    private User userPkey;

    @JoinColumn(name = "role_fkey", referencedColumnName = "role_id")
    @ManyToOne(optional = false)
    @Id
    private Role rolePkey;
}
