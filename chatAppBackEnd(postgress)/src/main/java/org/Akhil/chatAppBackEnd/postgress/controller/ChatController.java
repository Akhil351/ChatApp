package org.Akhil.chatAppBackEnd.postgress.controller;

import org.Akhil.chatAppBackEnd.postgress.entity.Message;
import org.Akhil.chatAppBackEnd.postgress.entity.Room;
import org.Akhil.chatAppBackEnd.postgress.exception.NotFound;
import org.Akhil.chatAppBackEnd.postgress.payload.MessageRequest;
import org.Akhil.chatAppBackEnd.postgress.payload.MessageResponse;
import org.Akhil.chatAppBackEnd.postgress.repo.MessageRepo;
import org.Akhil.chatAppBackEnd.postgress.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private MessageRepo messageRepo;
    // for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}") // /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}")
    public MessageResponse sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request){
        Room room=roomRepo.findByRoomId(roomId).orElseThrow(()-> new NotFound("Room not found!"));
        Message message= Message.builder().sender(request.getSender()).content(request.getContent()).room(room).build();
        messageRepo.save(message);
        return MessageResponse.builder()
                .sender(message.getSender())
                .content(message.getContent())
                .timeStamp(message.getTimeStamp())
                .build();
    }
}
