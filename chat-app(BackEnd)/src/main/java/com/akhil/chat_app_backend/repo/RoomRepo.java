package com.akhil.chat_app_backend.repo;


import com.akhil.chat_app_backend.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoomRepo extends MongoRepository<Room,String> {
    Optional<Room> findByRoomId (String roomId);
}
