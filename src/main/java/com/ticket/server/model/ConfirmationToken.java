package com.ticket.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String token;
    private  Date createdAt;
    private  Date expiredAt;
    private  Date confirmAt;

    @ManyToOne
    @JoinColumn(nullable = false,name = "user")
    private User user;
}
