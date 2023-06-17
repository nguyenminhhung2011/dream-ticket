package com.ticket.server.model;

import com.ticket.server.entities.Airport;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class StopAirport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @Column(nullable = false)
    private LocalDateTime stopTime;

    private String description;
}
