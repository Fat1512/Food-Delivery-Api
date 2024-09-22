package com.food.phat.dto.socket;

import lombok.Data;

import java.util.Date;

@Data
public class ChatMessageRequest {
    private Integer chatMessageId;
    private String content;
    private Date createdAt;
    private Integer chatRoomId;
    private Integer userId;
}
