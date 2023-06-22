package com.ticket.server.exceptions;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<?> handlerForbiddenException(HttpClientErrorException.Forbidden ex, WebRequest req){
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .date(new Timestamp(new Date().getTime()))
                        .message(ex.getMessage())
                        .build()
                ,HttpStatus.FORBIDDEN
        );
    }
}
