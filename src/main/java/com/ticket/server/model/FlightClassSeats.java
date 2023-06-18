package com.ticket.server.model;

import com.ticket.server.entities.Flight;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "flight_class_Seat")
public class FlightClassSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Flight flight;

    @ManyToOne
    private FlightClass flightClass;

    private Integer numberOfSeats;
    private Double price;
}
