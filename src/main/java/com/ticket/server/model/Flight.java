package com.ticket.server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;
    private Duration flightDuration;
}
