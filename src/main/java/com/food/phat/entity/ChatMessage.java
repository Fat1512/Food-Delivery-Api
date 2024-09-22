package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="chat_message")
@Data
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chat_message_id")
    private Integer chatMessageId;

    @Column(name="content")
    private String content;

    @Column(name="created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="user_fkey")
    private User user;

}
