package com.food.phat.mapstruct.user;

import com.food.phat.dto.socket.UserSocketResponse;
import com.food.phat.entity.User;
import com.food.phat.mapstruct.user.decorator.UserDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(UserDecorator.class)
public interface UserMapper {
    UserSocketResponse toUserSocketResponse(User user);
}
