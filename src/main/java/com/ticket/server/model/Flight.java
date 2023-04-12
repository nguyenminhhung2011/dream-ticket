package com.ticket.server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;

    private Long airplane;
    private Long departure;
    private Long arrival;
    private Date departureDate;
    private Date arrivalDate;
    private Boolean isStop;
}
