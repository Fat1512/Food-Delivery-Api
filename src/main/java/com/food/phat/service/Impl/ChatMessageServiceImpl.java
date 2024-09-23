package com.food.phat.service.Impl;

import com.food.phat.dto.socket.ChatMessageRequest;
import com.food.phat.dto.socket.ChatMessageResponse;
import com.food.phat.entity.ChatMessage;
import com.food.phat.entity.ChatRoom;
import com.food.phat.entity.ChatRoomUser;
import com.food.phat.mapstruct.ChatMessageMapper;
import com.food.phat.repository.ChatMessageRepository;
import com.food.phat.repository.ChatRoomRepository;
import com.food.phat.service.ChatMessageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Override
    @Transactional
    public ChatMessageResponse save(ChatMessageRequest chatMessageRequest) {

        ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageRequest);
        ChatRoom chatRoom = chatRoomRepository.findById(chatMessageRequest.getChatRoomId()).get();

        chatMessage = chatMessageRepository.save(chatMessage);
        chatRoom.addChatMessage(chatMessage);

        chatRoomRepository.save(chatRoom);
        return chatMessageMapper.toDto(chatMessage);

//        AtomicReference<ChatRoomUser> chatRoomUser = new AtomicReference<>();
//        chatRoom.getChatRoomUsers().forEach(chatRoomUserr ->  {
//            if(Objects.equals(chatRoomUserr.getUser().getUserId(), chatMessageRequest.getUserId())) chatRoomUser.set(chatRoomUserr);
//        });
    }
}
