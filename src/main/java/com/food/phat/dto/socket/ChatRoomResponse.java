package com.food.phat.dto.socket;


import lombok.Data;

import java.util.List;

@Data
public class ChatRoomResponse {
    private String chatRoomId;
    private List<UserSocketResponse> userInfo;
}
