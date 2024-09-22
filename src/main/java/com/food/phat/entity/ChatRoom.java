package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="chat_room")
@Data
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chat_room_id")
    private Integer chatRoomId;

    @ManyToMany
    @JoinTable(
            name="chat_room_user",
            joinColumns = @JoinColumn(name="chat_room_fkey"),
            inverseJoinColumns = @JoinColumn(name="user_fkey")
    )
    private List<User> users;

    @OneToMany
    @JoinColumn(name="chat_room_fkey")
    private List<ChatMessage> chatMessages;

    public void addChatMessage(ChatMessage chatMessage) {
        if(this.chatMessages == null){
            this.chatMessages = new ArrayList<>();
        }
        this.chatMessages.add(chatMessage);
    }
}
