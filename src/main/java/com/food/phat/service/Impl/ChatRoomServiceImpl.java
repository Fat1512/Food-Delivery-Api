package com.food.phat.service.Impl;

import com.food.phat.dto.socket.ChatRoomRequest;
import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.entity.ChatRoom;
import com.food.phat.entity.ChatRoomUser;
import com.food.phat.entity.Role;
import com.food.phat.entity.User;
import com.food.phat.mapstruct.ChatRoomMapper;
import com.food.phat.repository.ChatRoomRepository;
import com.food.phat.repository.RoleRepository;
import com.food.phat.repository.UserRepository;
import com.food.phat.service.ChatRoomService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ChatRoomMapper chatRoomMapper;


    @Override
    @Transactional
    public ChatRoomResponse getChatRoomInfo(ChatRoomRequest chatRoomRequest) {
        ChatRoom chatRoomm = chatRoomRepository.findById(chatRoomRequest.getChatRoomId()).or(() -> {
            ChatRoom chatRoom = new ChatRoom();
            chatRoomRequest.getUserInfo().forEach((userId, roleId) -> {
                ChatRoomUser chatRoomUser = new ChatRoomUser();

                ChatRoomUser.ChatRoomUserId chatRoomUserId = new ChatRoomUser.ChatRoomUserId();
                chatRoomUserId.setUserId(userId);
                chatRoomUserId.setChatRoomId(chatRoomRequest.getChatRoomId());

                User user = userRepository.findById(userId).get();
                Role role = roleRepository.findById(roleId).get();
                chatRoomUser.setUser(user);
                chatRoomUser.setChatRoom(chatRoom);
                chatRoomUser.setRole(role);
                chatRoomUser.setChatRoomUserId(chatRoomUserId);
                chatRoom.addChatRoomUser(chatRoomUser);
            });
            return Optional.of(chatRoomRepository.save(chatRoom));
        }).get();
        return chatRoomMapper.toDto(chatRoomm);
    }
}


/**
 *
 * getUserBasedOnRoleId
 * create chat room user
 * create chat room
 *
 */