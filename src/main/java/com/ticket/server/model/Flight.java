package com.ticket.server.model;

import com.ticket.server.entities.Airport;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private int distance;

    private LocalDateTime arrivalTime;

    @OneToMany(mappedBy = "flight")
    private List<FlightClassSeats> flightClassSeatsList;

    @OneToMany(mappedBy = "flight")
    private List<StopAirport> stops;
}
