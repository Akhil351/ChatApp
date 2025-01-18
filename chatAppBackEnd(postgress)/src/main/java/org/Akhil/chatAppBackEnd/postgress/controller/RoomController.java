package org.Akhil.chatAppBackEnd.postgress.controller;


import org.Akhil.chatAppBackEnd.postgress.config.AppConstants;
import org.Akhil.chatAppBackEnd.postgress.entity.Message;
import org.Akhil.chatAppBackEnd.postgress.entity.Room;
import org.Akhil.chatAppBackEnd.postgress.payload.Response;
import org.Akhil.chatAppBackEnd.postgress.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(AppConstants.FRONT_END_BASE_URL)
public class RoomController {
    @Autowired
    private RoomService roomService;
    // create room
    @PostMapping
    public ResponseEntity<Response> createRoom(@RequestBody String roomId){
        Room room=roomService.createRoom(roomId);
        return ResponseEntity.status(CREATED).body(Response.builder().status("Success").data(room).error(null).build());
    }
    // join room
    @GetMapping("/{roomId}")
    public ResponseEntity<Response> joinRoom(@PathVariable (name = "roomId") String roomId){
        Room room=roomService.joinRoom(roomId);
        return ResponseEntity.ok(Response.builder().status("Success").data(room).error(null).build());
    }

    // get message of room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<Response> getMessages(@PathVariable String roomId,@RequestParam(value="page",defaultValue = "0",required = false) int page
            ,@RequestParam(value="size",defaultValue = "20",required = false) int size){
        List<Message> messages=roomService.getMessages(roomId);
        if (messages.isEmpty()) {
            return ResponseEntity.ok(Response.builder().status("Success").data(new ArrayList<>()).error(null).build());
        }
        messages.sort(Comparator.comparing(Message::getTimeStamp));
        int start=page*size;
        int end=Math.min(start+size,messages.size());
        if (start>= messages.size()){
            return ResponseEntity.status(BAD_REQUEST).body(Response.builder().status("Success").data("Invalid page number!").error(null).build());
        }
        List<Message> paginatedMessage=messages.subList(start,end);
        return ResponseEntity.ok(Response.builder().status("Success").data(paginatedMessage).error(null).build());

    }


}
