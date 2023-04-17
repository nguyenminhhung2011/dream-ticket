package com.ticket.server.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String accountName;

    @Column(length = 50, nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_information_id", referencedColumnName = "id")
    private UserInformation userInformation;

    @PrePersist
    public void createUserInfo() {
        UserInformation userInfo = new UserInformation();
        userInfo.setId(this.id);
        this.userInformation = userInfo;
    }
}
