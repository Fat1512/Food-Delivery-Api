package com.food.phat.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Table(name="user")
@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="gender")
    private Boolean gender;

    @Column(name="phone")
    private String phone;

    @Column(name="birthdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    @Column(name="status")
    private Boolean status;

    @Column(name="avatar")
    private String avatar;

    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(name="last_accessed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccessed;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name="user_fkey"),
            inverseJoinColumns = @JoinColumn(name="role_fkey"))
    @JsonIgnore
    List<Role> roles;

    public User() {

    }
}
