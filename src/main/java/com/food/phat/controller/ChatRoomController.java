package com.food.phat.controller;

import com.food.phat.dto.MessageResponse;
import com.food.phat.dto.socket.ChatRoomDetailResponse;
import com.food.phat.dto.socket.ChatRoomRequest;
import com.food.phat.dto.socket.ChatRoomResponse;
import com.food.phat.service.ChatRoomService;
import com.food.phat.service.Impl.UserServiceImpl;
import com.food.phat.utils.ApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/chatroom")
    public ResponseEntity<MessageResponse> getChatRoomInfo(@RequestBody ChatRoomRequest chatRoomRequest) {
        ChatRoomDetailResponse chatRoomResponse = chatRoomService.getChatRoomInfo(chatRoomRequest);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(chatRoomResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/chatrooms")
    public ResponseEntity<?> getChatRooms(Principal principal) {
        Integer userId = userServiceImpl.getUserByUsername(principal.getName()).getUserId();
        List<ChatRoomResponse> chatRoomResponseList = chatRoomService.getChatRoomInfo(userId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(chatRoomResponseList)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}
