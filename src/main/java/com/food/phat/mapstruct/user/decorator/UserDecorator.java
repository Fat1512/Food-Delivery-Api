package com.food.phat.mapstruct.user.decorator;

import com.food.phat.dto.socket.UserSocketResponse;
import com.food.phat.entity.ChatRoomUser;
import com.food.phat.entity.Restaurant;
import com.food.phat.mapstruct.user.UserMapper;
import com.food.phat.repository.RestaurantRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public abstract class UserDecorator implements UserMapper {

    @Qualifier("delegate")
    @Autowired
    private UserMapper delegate;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public UserSocketResponse toUserSocketResponse(ChatRoomUser chatRoomUser) {
        UserSocketResponse userSocketResponse = delegate.toUserSocketResponse(chatRoomUser);

        userSocketResponse.setUserId(chatRoomUser.getUser().getUserId());
        userSocketResponse.setRole(chatRoomUser.getRole().getId());
        userSocketResponse.setStatus(chatRoomUser.getUser().getStatus());
        userSocketResponse.setChatRoomId(chatRoomUser.getChatRoom().getChatRoomId());
        if(chatRoomUser.getRole().getId() == 2) { //user role
            userSocketResponse.setName(chatRoomUser.getUser().getFirstName());
            userSocketResponse.setAvatar(chatRoomUser.getUser().getAvatar());
        } else {
            Restaurant restaurant = restaurantRepository.findByUserId(chatRoomUser.getUser().getUserId());
            userSocketResponse.setName(restaurant.getName());
            userSocketResponse.setAvatar(restaurant.getAvatarImage());
        }
        return userSocketResponse;
    }
}









