package com.substring.chat.chat_app_backend.controller;

import com.substring.chat.chat_app_backend.configuration.AppConstants;
import com.substring.chat.chat_app_backend.model.Message;
import com.substring.chat.chat_app_backend.playload.MessageRequest;
import com.substring.chat.chat_app_backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin(AppConstants.FRONT_END_BASE_URL)
public class ChatController
{

    @Autowired
    private ChatService chatService;

    @MessageMapping("/sendMessage/{roomId}") // /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}") // subscribe
    public Message sendMessage(@DestinationVariable String roomId,
                                   @RequestBody MessageRequest message)
    {
        return chatService.sendMessage(roomId, message);
    }
}
