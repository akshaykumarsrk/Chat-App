package com.substring.chat.chat_app_backend.controller;

import com.substring.chat.chat_app_backend.model.Message;
import com.substring.chat.chat_app_backend.model.Room;
import com.substring.chat.chat_app_backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
@CrossOrigin("http://localhost:3000")
public class RoomController
{

    @Autowired
    private RoomService roomService;

    // create room
    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody String roomId)
    {
        return roomService.createRoom(roomId);
    }

    // get room
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId)
    {
        return roomService.joinRoom(roomId);
    }

    // get messages of room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId,
                                                     @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                     @RequestParam(value = "size", defaultValue = "20", required = false) int size)
    {
        return roomService.getMessages(roomId, page, size);
    }
}
