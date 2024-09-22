package com.food.phat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Table(name="chat_room_user")
@Entity
@DynamicUpdate
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomUser {

    @EmbeddedId
    private ChatRoomUserId chatRoomUserId;

    @JoinColumn(name="user_fkey")
    @ManyToOne(optional = false)
    @MapsId("userId")
    private User user;

    @JoinColumn(name="chat_room_fkey")
    @ManyToOne(optional = false)
    @MapsId("chatRoomId")
    private ChatRoom chatRoom;

    @JoinColumn(name="role_fkey")
    @ManyToOne(optional = false)
    private Role role;

    @Embeddable
    @Setter
    @Getter
    @AllArgsConstructor
    public static class ChatRoomUserId implements Serializable {
        @Column(name="chat_room_fkey")
        private int chatRoomId;

        @Column(name="user_fkey")
        private int userId;

        public ChatRoomUserId() {

        }
    }
}
