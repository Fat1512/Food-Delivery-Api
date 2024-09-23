package com.food.phat.service;

import com.food.phat.dto.socket.ChatRoomDetailResponse;
import com.food.phat.dto.socket.ChatRoomRequest;
import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.dto.socket.UserSocketResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatRoomService {
    ChatRoomDetailResponse getChatRoomInfo(ChatRoomRequest chatRoomRequest);
    List<ChatRoomResponse> getChatRoomInfo(Integer userId);
    List<UserSocketResponse> getChatRoomUserBasedOnUserId(Integer userId);
}
