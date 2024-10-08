package com.food.phat.dto.socket;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatRoomDetailResponse {
    private String chatRoomId;
    private List<UserSocketResponse> userInfo;
    private List<ChatMessageResponse> chatMessages;
}
