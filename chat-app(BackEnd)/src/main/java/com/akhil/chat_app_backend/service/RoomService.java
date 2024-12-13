package com.akhil.chat_app_backend.service;

import com.akhil.chat_app_backend.entity.Message;
import com.akhil.chat_app_backend.entity.Room;

import java.util.List;

public interface RoomService {
    public Room createRoom(String roomId);
    public Room joinRoom(String roomId);
    public List<Message> getMessages(String roomId);
}
