package com.ticket.server.enums;

public enum Gender {
    MALE(true),
    FEMALE(false),
    ;

    public final boolean gender;

    Gender(boolean value){
        gender = value;
    }
}
