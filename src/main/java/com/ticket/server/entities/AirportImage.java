package com.ticket.server.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "airport_images")
public class AirportImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Airport airport;

    @Column(nullable = false)
    private String url;
}
