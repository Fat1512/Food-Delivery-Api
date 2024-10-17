package com.food.phat.controller;

import com.food.phat.dto.socket.UserSocketResponse;
import com.food.phat.entity.User;
import com.food.phat.service.ChatRoomService;
import com.food.phat.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PresenceController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserServiceImpl userServiceImpl;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/user.onConnected")
    public void userOnConnected(Principal principal) {
        User user = userServiceImpl.getUserByUsername(principal.getName());

        List<UserSocketResponse> userSocketResponses = chatRoomService
                .getChatRoomUserBasedOnUserId(user.getUserId());

        userSocketResponses.forEach(userSocketResponse ->
            simpMessagingTemplate.convertAndSendToUser(
                    userSocketResponse.getChatRoomId().toString(),
                    "/queue/online_users",
                    userSocketResponse));
    }

    @MessageMapping("/user-disconnected")
    public void userOnDisconnected(Principal principal) {
        User user = userServiceImpl.getUserByUsername(principal.getName());

        List<UserSocketResponse> userSocketResponses = chatRoomService
                .getChatRoomUserBasedOnUserId(user.getUserId());

        userSocketResponses.forEach(userSocketResponse ->
                simpMessagingTemplate.convertAndSendToUser(
                        userSocketResponse.getChatRoomId().toString(),
                        "/queue/online_users",
                        userSocketResponse));
    }
}













