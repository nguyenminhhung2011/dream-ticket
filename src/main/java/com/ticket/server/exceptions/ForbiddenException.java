package com.ticket.server.exceptions;

public class ForbiddenException extends Exception{
    public ForbiddenException(String message){
        super(message);
    }
}
