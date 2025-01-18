package org.Akhil.chatAppBackEnd.postgress.service.impl;

import org.Akhil.chatAppBackEnd.postgress.entity.Message;
import org.Akhil.chatAppBackEnd.postgress.entity.Room;
import org.Akhil.chatAppBackEnd.postgress.exception.AlreadyExist;
import org.Akhil.chatAppBackEnd.postgress.exception.NotFound;
import org.Akhil.chatAppBackEnd.postgress.repo.MessageRepo;
import org.Akhil.chatAppBackEnd.postgress.repo.RoomRepo;
import org.Akhil.chatAppBackEnd.postgress.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Override
    public Room createRoom(String roomId) {
        roomRepo.findByRoomId(roomId).ifPresent(existingRoom->{throw new AlreadyExist("Room already exists");
        });
        Room room=Room.builder().roomId(roomId).build();
        return roomRepo.save(room);
    }

    @Override
    public Room joinRoom(String roomId) {
        return roomRepo.findByRoomId(roomId).orElseThrow(()->new NotFound("Room not found!"));
    }

    @Override
    public List<Message> getMessages(String roomId) {
        Room room= roomRepo.findByRoomId(roomId).orElseThrow(()->new NotFound("Room not found!"));
        return messageRepo.findByRoom(room);
    }
}
