package com.ticket.server.model;

import com.ticket.server.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRegister {
    private String username;
    private String password;
    private String fullName;
    private String identityCard;
    private String phone;
    private String email;
    private String address;
    private Gender gender;

    public UserRegister(String username, String password, String fullName, String identityCard, String phone, String email, String address, Gender gender) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.identityCard = identityCard;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }
}
