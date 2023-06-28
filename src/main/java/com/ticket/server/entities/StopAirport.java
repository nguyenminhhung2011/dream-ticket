package com.ticket.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ticket.server.entities.Airport;
import com.ticket.server.entities.Flight;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "stop_airport")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StopAirport {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private StopAirportId id;

    @Column(nullable = false)
    private long stopTime;

    @Column()
    private String description;

}
