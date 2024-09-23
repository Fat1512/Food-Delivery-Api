package com.food.phat.mapstruct;


import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.entity.ChatRoom;
import com.food.phat.mapstruct.decorator.ChatRoomDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(ChatRoomDecorator.class)
public interface ChatRoomMapper {

    ChatRoomResponse toDto(ChatRoom chatRoom);
}
