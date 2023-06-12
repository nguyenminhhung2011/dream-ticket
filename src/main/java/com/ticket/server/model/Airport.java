package com.ticket.server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String airportName;

    @Column(nullable = false)
    private String location;
    @Column
    private  String imageUrl;
    @Column
    private  String description;

    @Column
    private LocalDateTime openTime;

    @Column
    private LocalDateTime closeTime;

    @OneToMany(mappedBy = "airport")
    private List<AirportImage> images;
}
