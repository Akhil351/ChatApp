package org.Akhil.chatAppBackEnd.postgress.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {
    private String content;
    private String sender;
    private LocalDateTime timeStamp;
}
