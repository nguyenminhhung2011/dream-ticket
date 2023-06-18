package com.ticket.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tokenId")
    private Long id;

    @Column(unique = true)
    private String token;
    private Date expiredTime;
    private boolean isExpired;
    private boolean isRevoke;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public User user;

}
