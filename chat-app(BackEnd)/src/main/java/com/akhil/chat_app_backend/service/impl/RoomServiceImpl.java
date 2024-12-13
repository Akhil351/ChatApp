package com.akhil.chat_app_backend.service.impl;

import com.akhil.chat_app_backend.entity.Message;
import com.akhil.chat_app_backend.entity.Room;
import com.akhil.chat_app_backend.exception.AlreadyExist;
import com.akhil.chat_app_backend.exception.NotFound;
import com.akhil.chat_app_backend.repo.RoomRepo;
import com.akhil.chat_app_backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepo roomRepo;
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
        return room.getMessages();
    }
}
