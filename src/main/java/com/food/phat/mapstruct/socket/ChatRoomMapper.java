package com.food.phat.mapstruct.socket;


import com.food.phat.dto.socket.ChatRoomDetailResponse;
import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.entity.ChatRoom;
import com.food.phat.mapstruct.socket.decorator.ChatRoomDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(ChatRoomDecorator.class)
public interface ChatRoomMapper {

    ChatRoomDetailResponse toChatRoomDetailResponse(ChatRoom chatRoom);
    ChatRoomResponse toChatRoomResponse(ChatRoom chatRoom);

}
