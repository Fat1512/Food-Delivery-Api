package com.food.phat.mapstruct.decorator;

import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.entity.ChatRoom;
import com.food.phat.entity.User;
import com.food.phat.mapstruct.ChatRoomMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public abstract class ChatRoomDecorator implements ChatRoomMapper {

    @Qualifier("delegate")
    @Autowired
    private ChatRoomMapper delegate;

    @Override
    public ChatRoomResponse toDto(ChatRoom chatRoom) {
        ChatRoomResponse chatRoomResponse = delegate.toDto(chatRoom);
        chatRoomResponse.setUserIds(chatRoom.getUsers().stream().map(User::getUserId).toList());
        return chatRoomResponse;
    }
}
