package org.Akhil.chatAppBackEnd.postgress.repo;


import org.Akhil.chatAppBackEnd.postgress.entity.Message;
import org.Akhil.chatAppBackEnd.postgress.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepo extends JpaRepository<Message,Long> {
    List<Message> findByRoom(Room room);
}
