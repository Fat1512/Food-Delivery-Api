package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="comment_restaurant")
@Data
public class CommentRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer commentId;

    @Column(name="starCount")
    private Integer starCount;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name="comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name="restaurant_fkey")
    private Restaurant restaurant;
}
