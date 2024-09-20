package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="comment_history")
@Data
public class CommentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_history_id")
    private Integer commentId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="comment_fkey")
    private Comment comment;

    @Column(name="content")
    private String content;

    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;


}
