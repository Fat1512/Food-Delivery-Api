package com.food.phat.mapstruct.user.decorator;


import com.food.phat.dto.socket.UserSocketResponse;
import com.food.phat.entity.User;
import com.food.phat.mapstruct.ChatMessageMapper;
import com.food.phat.mapstruct.user.UserMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public abstract class UserDecorator implements UserMapper {

    @Qualifier("delegate")
    @Autowired
    private ChatMessageMapper delegate;

//    @Override
//    public UserSocketResponse toUserSocketResponse(User user) {
//
//    }
}
