package com.food.phat.service.Impl;

import com.food.phat.dto.socket.ChatRoomDetailResponse;
import com.food.phat.dto.socket.ChatRoomRequest;
import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.dto.socket.UserSocketResponse;
import com.food.phat.entity.ChatRoom;
import com.food.phat.entity.ChatRoomUser;
import com.food.phat.entity.Role;
import com.food.phat.entity.User;
import com.food.phat.mapstruct.socket.ChatRoomMapper;
import com.food.phat.mapstruct.user.UserMapper;
import com.food.phat.repository.ChatRoomRepository;
import com.food.phat.repository.ChatRoomUserRepository;
import com.food.phat.repository.RoleRepository;
import com.food.phat.repository.UserRepository;
import com.food.phat.service.ChatRoomService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private ChatRoomUserRepository chatRoomUserRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ChatRoomMapper chatRoomMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    public ChatRoomDetailResponse getChatRoomInfo(ChatRoomRequest chatRoomRequest) {
        if(chatRoomRequest.getChatRoomId() != null)
            return chatRoomMapper.toChatRoomDetailResponse(chatRoomRepository.findById(chatRoomRequest.getChatRoomId()).get());

        ChatRoom chatRoom = new ChatRoom();
        chatRoomRequest.getUserInfo().forEach((userId, roleId) -> {
            ChatRoomUser chatRoomUser = new ChatRoomUser();

            ChatRoomUser.ChatRoomUserId chatRoomUserId = new ChatRoomUser.ChatRoomUserId();
            chatRoomUserId.setUserId(userId);

            User user = userRepository.findById(userId).get();
            Role role = roleRepository.findById(roleId).get();
            chatRoomUser.setUser(user);
            chatRoomUser.setChatRoom(chatRoom);
            chatRoomUser.setRole(role);
            chatRoomUser.setChatRoomUserId(chatRoomUserId);
            chatRoom.addChatRoomUser(chatRoomUser);
        });

        return chatRoomMapper.toChatRoomDetailResponse(chatRoomRepository.save(chatRoom));
    }

    @Override
    @Transactional
    public List<ChatRoomResponse> getChatRoomInfo(Integer userId) {
        return chatRoomRepository.findByUserId(userId).stream()
                .map(chatRoom -> chatRoomMapper.toChatRoomResponse(chatRoom)).toList();
    }

    @Override
    @Transactional
    public List<UserSocketResponse> getChatRoomUserBasedOnUserId(Integer userId) {
          return chatRoomUserRepository.getChatRoomUserBasedOnUserId(userId).stream()
                  .map(chatRoomUser -> userMapper.toUserSocketResponse(chatRoomUser))
                  .toList();
    }
}


/**
 *
 * getUserBasedOnRoleId
 * create chat room user
 * create chat room
 *
 */