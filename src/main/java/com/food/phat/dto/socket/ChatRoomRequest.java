package com.food.phat.dto.socket;

import lombok.Data;

import java.util.Map;

@Data
public class ChatRoomRequest {
    private Integer chatRoomId;
    private Map<Integer, Integer> userInfo;
    //user_id + user_role_id
}
