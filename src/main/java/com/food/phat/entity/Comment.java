package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Integer commentId;

    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name="user_fkey")
    private User user;

    @Column(name="lft")
    private Integer lft;

    @Column(name="rgt")
    private Integer rgt;
}
