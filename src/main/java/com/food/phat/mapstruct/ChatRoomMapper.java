package com.food.phat.mapstruct;


import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.entity.ChatRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={ChatMessageMapper.class})
public interface ChatRoomMapper {

    ChatRoomResponse toDto(ChatRoom chatRoom);
}
