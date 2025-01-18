package org.Akhil.chatAppBackEnd.postgress.exception;

import org.Akhil.chatAppBackEnd.postgress.payload.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(AlreadyExist.class)
    public ResponseEntity<Response> roomAlreadyExists(AlreadyExist exception){
        return ResponseEntity.status(CONFLICT).body(Response.builder().status("Failed").data(null).error(exception.getMessage()).build());
    }
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Response> roomNotFound(NotFound exception){
        return ResponseEntity.status(NOT_FOUND).body(Response.builder().status("Failed").data(null).error(exception.getMessage()).build());
    }

}
