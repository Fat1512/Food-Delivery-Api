package com.food.phat.service;

import com.food.phat.dto.socket.ChatMessageRequest;
import com.food.phat.dto.socket.ChatMessageResponse;

public interface ChatMessageService {
    ChatMessageResponse save(ChatMessageRequest chatMessageRequest);
}
