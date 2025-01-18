package org.Akhil.chatAppBackEnd.postgress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String content;
    @Builder.Default
    private LocalDateTime timeStamp=LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

}
