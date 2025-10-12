package com.substring.chat.chat_app_backend.service;

import com.substring.chat.chat_app_backend.model.Message;
import com.substring.chat.chat_app_backend.model.Room;
import com.substring.chat.chat_app_backend.playload.MessageRequest;
import com.substring.chat.chat_app_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService
{

    @Autowired
    private RoomRepository roomRepository;

    public ChatService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // for sending and receiving messages

    public Message sendMessage(String roomId, MessageRequest request) {
        Room room = roomRepository.findByRoomId(request.getRoomId());
        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        if(room != null)
        {
            room.getMessages().add(message);
            roomRepository.save(room);
        }
        else
        {
            throw new RuntimeException("Room not found");
        }

        return message;
    }
}
