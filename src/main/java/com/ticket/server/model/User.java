package com.ticket.server.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String accountName;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 10)
    private String firstName;

    @Column(length = 10)
    private String lastName;

    @Column(length = 20)
    private String identityCard;

    @Column(length = 20)
    private String phone;

    @Column(length = 50)
    private String email;

    //True is male, False is female
    private Boolean gender;

    // role: 0 is admin, role: 1 is manager and role: 2 is customer
    @Column(length = 1)
    private Integer role;
}
