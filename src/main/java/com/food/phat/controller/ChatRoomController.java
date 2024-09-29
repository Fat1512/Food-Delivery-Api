package com.food.phat.controller;

import com.food.phat.dto.socket.ChatRoomRequest;
import com.food.phat.service.ChatRoomService;
import com.food.phat.service.Impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final UserService userService;

    @GetMapping("/chatroom")
    public ResponseEntity<?> getChatRoomInfo(@RequestBody ChatRoomRequest chatRoomRequest) {
        return new ResponseEntity<>(chatRoomService.getChatRoomInfo(chatRoomRequest), HttpStatus.OK);
    }

    @GetMapping("/chatrooms")
    public ResponseEntity<?> getChatRooms(Principal principal) {
        Integer userId = userService.getUserByUsername(principal.getName()).getUserId();
        return new ResponseEntity<>(chatRoomService.getChatRoomInfo(userId), HttpStatus.OK);
    }

}
