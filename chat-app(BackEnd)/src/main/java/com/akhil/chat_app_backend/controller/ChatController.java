package com.akhil.chat_app_backend.controller;

import com.akhil.chat_app_backend.config.AppConstants;
import com.akhil.chat_app_backend.entity.Message;
import com.akhil.chat_app_backend.entity.Room;
import com.akhil.chat_app_backend.exception.NotFound;
import com.akhil.chat_app_backend.payload.MessageRequest;
import com.akhil.chat_app_backend.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(AppConstants.FRONT_END_BASE_URL)
public class ChatController {

    @Autowired
    private RoomRepo roomRepo;

    // for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}") // /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request){
        Room room=roomRepo.findByRoomId(roomId).orElseThrow(()-> new NotFound("Room not found!"));
        Message message= Message.builder().sender(request.getSender()).content(request.getContent()).build();
        room.getMessages().add(message);
        roomRepo.save(room);
        return message;
    }
}
