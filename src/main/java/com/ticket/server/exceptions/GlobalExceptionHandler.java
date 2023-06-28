package com.ticket.server.exceptions;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {HttpClientErrorException.Forbidden.class})
    public ResponseEntity<?> handlerForbiddenException(HttpClientErrorException.Forbidden ex, WebRequest req){
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .date(new Timestamp(new Date().getTime()))
                        .message(ex.getMessage())
                        .build()
                ,HttpStatus.FORBIDDEN
        );
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception ex, WebRequest req){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),new Timestamp(new Date().getTime()));
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException ex, WebRequest req){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),new Timestamp(new Date().getTime()));
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<?> handlerUnauthorizedException(UnauthorizedException ex, WebRequest req){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),new Timestamp(new Date().getTime()));
        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<?> handlerNotFoundException(ForbiddenException ex, WebRequest req){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),new Timestamp(new Date().getTime()));
        return new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }
}
