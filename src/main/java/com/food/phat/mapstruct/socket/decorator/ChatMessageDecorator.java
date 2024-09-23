package com.food.phat.mapstruct.socket.decorator;

import com.food.phat.dto.socket.ChatMessageRequest;
import com.food.phat.dto.socket.ChatMessageResponse;
import com.food.phat.entity.ChatMessage;
import com.food.phat.mapstruct.socket.ChatMessageMapper;
import com.food.phat.repository.RestaurantRepository;
import com.food.phat.repository.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public abstract class ChatMessageDecorator implements ChatMessageMapper {

    @Qualifier("delegate")
    @Autowired
    private ChatMessageMapper delegate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public ChatMessageResponse toDto(ChatMessage chatMessage) {
        ChatMessageResponse chatMessageResponse = delegate.toDto(chatMessage);
        chatMessageResponse.setUserId(chatMessage.getUser().getUserId());

        return chatMessageResponse;
    }

    @Override
    public ChatMessage toEntity(ChatMessageRequest chatMessageRequest) {
        ChatMessage chatMessage = delegate.toEntity(chatMessageRequest);
        chatMessage.setUser(userRepository.findById(chatMessageRequest.getUserId()).get());
        return chatMessage;
    }
}
