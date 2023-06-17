package com.ticket.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@Data
@Builder
@Entity
@RequiredArgsConstructor
public class AppToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private Date expiredTime;
    private boolean isExpired;
    private boolean isRevoke;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public User user;
}
