package com.ticket.server.model;

import com.ticket.server.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_information")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String identityCard;
    private String phone;
    private String email;
    private String address;
    private Gender gender;
}
