package com.ticket.server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "flight_class")
public class FlightClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "flightClass")
    private List<FlightClassSeats> flightClassSeatsList;
}
