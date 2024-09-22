package com.food.phat.service;

import com.food.phat.dto.socket.ChatRoomRequest;
import com.food.phat.dto.socket.ChatRoomResponse;
import org.springframework.stereotype.Service;

@Service
public interface ChatRoomService {
    ChatRoomResponse getChatRoomInfo(ChatRoomRequest chatRoomRequest);
}
