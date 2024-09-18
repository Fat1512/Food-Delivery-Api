package com.food.phat.entity;


import jakarta.persistence.*;

@Entity
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name="user_fkey")
    private User user;


}
