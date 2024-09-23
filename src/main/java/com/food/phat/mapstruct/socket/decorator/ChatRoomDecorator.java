package com.food.phat.mapstruct.socket.decorator;

import com.food.phat.dto.socket.ChatMessageResponse;
import com.food.phat.dto.socket.ChatRoomDetailResponse;
import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.dto.socket.UserSocketResponse;
import com.food.phat.entity.ChatRoom;
import com.food.phat.mapstruct.socket.ChatMessageMapper;
import com.food.phat.mapstruct.socket.ChatRoomMapper;
import com.food.phat.mapstruct.user.UserMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper
public abstract class ChatRoomDecorator implements ChatRoomMapper {

    @Qualifier("delegate")
    @Autowired
    private ChatRoomMapper delegate;
    @Autowired
    private ChatMessageMapper chatMessageMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ChatRoomDetailResponse toChatRoomDetailResponse(ChatRoom chatRoom) {
        ChatRoomDetailResponse chatRoomDetailResponse = delegate.toChatRoomDetailResponse(chatRoom);
        List<UserSocketResponse> userSocketResponses = chatRoom.getChatRoomUsers()
                .stream().map(chatRoomUser -> userMapper.toUserSocketResponse(chatRoomUser)).toList();

        if(chatRoom.getChatMessages() != null) {
            List<ChatMessageResponse> chatMessageResponses = chatRoom.getChatMessages()
                    .stream()
                    .map(chatMessage -> chatMessageMapper.toDto(chatMessage))
                    .toList();
            chatRoomDetailResponse.setChatMessages(chatMessageResponses);
        }

        chatRoomDetailResponse.setUserInfo(userSocketResponses);
        return chatRoomDetailResponse;
    }

    @Override
    public ChatRoomResponse toChatRoomResponse(ChatRoom chatRoom) {
        ChatRoomResponse chatRoomResponse = delegate.toChatRoomResponse(chatRoom);
        List<UserSocketResponse> userSocketResponses = chatRoom.getChatRoomUsers()
                .stream().map(chatRoomUser -> userMapper.toUserSocketResponse(chatRoomUser)).toList();

        chatRoomResponse.setUserInfo(userSocketResponses);
        return chatRoomResponse;
    }
}
