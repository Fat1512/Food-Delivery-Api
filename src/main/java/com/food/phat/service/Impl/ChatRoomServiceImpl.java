package com.food.phat.service.Impl;

import com.food.phat.dto.socket.ChatRoomRequest;
import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.mapstruct.ChatRoomMapper;
import com.food.phat.repository.ChatRoomRepository;
import com.food.phat.service.ChatRoomService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Autowired
    private ChatRoomMapper chatRoomMapper;

    @Override
    @Transactional
    public ChatRoomResponse getChatRoomInfo(ChatRoomRequest chatRoomRequest) {

        return null;
    }
}
/**
 *
 * getUserBasedOnRoleId
 * create chat room user
 * create chat room
 *
 */