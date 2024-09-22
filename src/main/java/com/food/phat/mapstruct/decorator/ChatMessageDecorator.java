package com.food.phat.mapstruct.decorator;

import com.food.phat.dto.socket.ChatMessageRequest;
import com.food.phat.dto.socket.ChatMessageResponse;
import com.food.phat.entity.ChatMessage;
import com.food.phat.entity.ChatRoomUser;
import com.food.phat.entity.Restaurant;
import com.food.phat.mapstruct.ChatMessageMapper;
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
    public ChatMessageResponse toDto(ChatMessage chatMessage, ChatRoomUser chatRoomUser) {
        ChatMessageResponse chatMessageResponse = delegate.toDto(chatMessage, chatRoomUser);
        chatMessageResponse.setUserId(chatMessage.getUser().getUserId());

        Integer roleId = chatRoomUser.getRole().getId();

        if(roleId == 0) {
            Restaurant restaurant = restaurantRepository.findByUserId(chatMessage.getUser().getUserId());
            chatMessageResponse.setName(restaurant.getName());
            chatMessageResponse.setAvatar(restaurant.getAvatarImage());
        }

        chatMessageResponse.setRoleId(roleId);
        return chatMessageResponse;
    }

    @Override
    public ChatMessage toEntity(ChatMessageRequest chatMessageRequest) {
        ChatMessage chatMessage = delegate.toEntity(chatMessageRequest);
        chatMessage.setUser(userRepository.findById(chatMessageRequest.getUserId()).get());
        return chatMessage;
    }
}
