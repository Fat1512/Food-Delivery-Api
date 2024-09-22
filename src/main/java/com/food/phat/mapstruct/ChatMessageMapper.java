package com.food.phat.mapstruct;


import com.food.phat.dto.socket.ChatMessageRequest;
import com.food.phat.dto.socket.ChatMessageResponse;
import com.food.phat.entity.ChatMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {

    ChatMessageResponse toDto(ChatMessage chatMessage);
    ChatMessage toEntity(ChatMessageRequest chatMessageRequest);

}
