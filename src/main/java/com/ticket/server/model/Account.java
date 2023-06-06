package com.ticket.server.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String accountName;

    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_information_id", referencedColumnName = "id")
    private UserInformation userInformation;
}
