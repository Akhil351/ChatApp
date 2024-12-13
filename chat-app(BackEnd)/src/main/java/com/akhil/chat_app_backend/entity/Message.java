package com.akhil.chat_app_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    private String sender;
    private String content;
    @Builder.Default
    private LocalDateTime timeStamp=LocalDateTime.now();

//    public Message(String sender, String content) {
//        this.sender = sender;
//        this.content = content;
//        this.timeStamp = LocalDateTime.now();
//    }
}
