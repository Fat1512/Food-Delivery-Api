package com.food.phat.dto.socket;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ChatMessageResponse {
    private Integer chatMessageId;
    private String content;
    private Date createdAt;
    private Integer userId;
    private Integer roleId;
    private String name;
    private String avatar;
}
