package com.food.phat.mapstruct.user;

import com.food.phat.dto.socket.UserSocketResponse;
import com.food.phat.entity.ChatRoomUser;
import com.food.phat.mapstruct.user.decorator.UserDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(UserDecorator.class)
public interface UserMapper {
    @Mapping(target="role", ignore = true)
    UserSocketResponse toUserSocketResponse(ChatRoomUser user);
}











