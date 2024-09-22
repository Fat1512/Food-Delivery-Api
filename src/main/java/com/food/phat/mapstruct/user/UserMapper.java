package com.food.phat.mapstruct.user;

import com.food.phat.dto.socket.UserSocketResponse;
import com.food.phat.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", source = )
    UserSocketResponse toUserSocketResponseDto(User user);
}
