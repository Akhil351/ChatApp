package org.Akhil.chatAppBackEnd.postgress.exception;

public class AlreadyExist extends RuntimeException {
    public AlreadyExist(String message) {
        super(message);
    }
}
