package com.food.phat.dto.socket;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatRoomResponse {
    private String chatRoomId;
    private List<Integer> userIds;
    private List<ChatMessageResponse> chatMessages;
}
