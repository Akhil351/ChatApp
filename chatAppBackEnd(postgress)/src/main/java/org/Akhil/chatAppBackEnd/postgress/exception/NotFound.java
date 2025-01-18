package org.Akhil.chatAppBackEnd.postgress.exception;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
