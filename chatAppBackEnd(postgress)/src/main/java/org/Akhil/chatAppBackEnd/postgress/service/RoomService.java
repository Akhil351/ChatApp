package org.Akhil.chatAppBackEnd.postgress.service;

import org.Akhil.chatAppBackEnd.postgress.entity.Message;
import org.Akhil.chatAppBackEnd.postgress.entity.Room;

import java.util.List;

public interface RoomService {
    public Room createRoom(String roomId);
    public Room joinRoom(String roomId);
    public List<Message> getMessages(String roomId);
}
