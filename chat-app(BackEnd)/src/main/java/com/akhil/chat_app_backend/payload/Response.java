package com.akhil.chat_app_backend.payload;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Response {
    private String status;
    @Builder.Default
    private LocalDateTime timeStamp=LocalDateTime.now();
    private Object data;
    private Object error;
}
