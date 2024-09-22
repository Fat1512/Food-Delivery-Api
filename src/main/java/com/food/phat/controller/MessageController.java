package com.food.phat.controller;

import com.food.phat.dto.socket.ChatMessageRequest;
import com.food.phat.dto.socket.ChatMessageResponse;
import com.food.phat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {

    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message.send")
    public void processMessage(@Payload ChatMessageRequest chatMessageRequest) {
        ChatMessageResponse chatMessageResponse = chatMessageService.save(chatMessageRequest);
        //user/{chatRoomId}/queue/messages
        simpMessagingTemplate.convertAndSendToUser(chatMessageRequest.getChatRoomId().toString()
                ,"/queue/messages", chatMessageResponse);
    }



}

/**
 *
 *  initialize chat room -> ()
 *
 *
 *
 */