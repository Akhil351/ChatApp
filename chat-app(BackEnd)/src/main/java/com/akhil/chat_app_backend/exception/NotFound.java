package com.akhil.chat_app_backend.exception;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
