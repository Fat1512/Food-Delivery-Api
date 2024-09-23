package com.food.phat.mapstruct.decorator;

import com.food.phat.dto.socket.ChatMessageResponse;
import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.dto.socket.UserSocketResponse;
import com.food.phat.entity.ChatRoom;
import com.food.phat.entity.Restaurant;
import com.food.phat.mapstruct.ChatMessageMapper;
import com.food.phat.mapstruct.ChatRoomMapper;
import com.food.phat.repository.RestaurantRepository;
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
    private RestaurantRepository restaurantRepository;

    @Override
    public ChatRoomResponse toDto(ChatRoom chatRoom) {
        ChatRoomResponse chatRoomResponse = delegate.toDto(chatRoom);
        List<UserSocketResponse> userSocketResponses = chatRoom.getChatRoomUsers().stream().map(chatRoomUser -> {

            UserSocketResponse userSocketResponse = new UserSocketResponse();
            userSocketResponse.setUserId(chatRoomUser.getUser().getUserId());
            userSocketResponse.setRole(chatRoomUser.getRole().getId());

            if(chatRoomUser.getRole().getId() == 2) { //user role
                userSocketResponse.setName(chatRoomUser.getUser().getFirstName());
                userSocketResponse.setAvatar(chatRoomUser.getUser().getAvatar());
            } else {
                Restaurant restaurant = restaurantRepository.findByUserId(chatRoomUser.getUser().getUserId());
                userSocketResponse.setName(restaurant.getName());
                userSocketResponse.setAvatar(restaurant.getAvatarImage());
            }
            return userSocketResponse;
        }).toList();


        if(chatRoom.getChatMessages() != null) {

            List<ChatMessageResponse> chatMessageResponses = chatRoom.getChatMessages()
                    .stream()
                    .map(chatMessage -> chatMessageMapper.toDto(chatMessage))
                    .toList();

            chatRoomResponse.setChatMessages(chatMessageResponses);
        }

        chatRoomResponse.setUserInfo(userSocketResponses);
        return chatRoomResponse;
    }
}
