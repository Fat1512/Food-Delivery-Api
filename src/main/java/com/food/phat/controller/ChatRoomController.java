package com.food.phat.controller;

import com.food.phat.dto.socket.ChatRoomRequest;
import com.food.phat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("/chatroom")
    public ResponseEntity<?> getChatRoomInfo(@RequestBody ChatRoomRequest chatRoomRequest) {
        return new ResponseEntity<>(chatRoomService.getChatRoomInfo(chatRoomRequest), HttpStatus.OK);
    }
}
