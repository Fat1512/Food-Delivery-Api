package com.food.phat.mapstruct.socket;


import com.food.phat.dto.socket.ChatMessageRequest;
import com.food.phat.dto.socket.ChatMessageResponse;
import com.food.phat.entity.ChatMessage;
import com.food.phat.mapstruct.socket.decorator.ChatMessageDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(ChatMessageDecorator.class)
public interface ChatMessageMapper {

    ChatMessageResponse toDto(ChatMessage chatMessage);
    ChatMessage toEntity(ChatMessageRequest chatMessageRequest);

}
