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

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChatRoomUser> chatRoomUsers;

    @OneToMany
    @JoinColumn(name="chat_room_fkey", nullable = false)
    private List<ChatMessage> chatMessages;

    public void addChatMessage(ChatMessage chatMessage) {
        if(this.chatMessages == null){
            this.chatMessages = new ArrayList<>();
        }
        this.chatMessages.add(chatMessage);
    }

    public void addChatRoomUser(ChatRoomUser chatRoomUser) {
        if(this.chatRoomUsers == null){
            this.chatRoomUsers = new ArrayList<>();
        }
        this.chatRoomUsers.add(chatRoomUser);
    }
}
