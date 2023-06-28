package com.ticket.server.dtos.FlightDtos;

import com.ticket.server.dtos.AirportDtos.StopAirportRequest;
import com.ticket.server.entities.Airline;
import com.ticket.server.entities.Airport;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
public class EditFlightDto {
    private Optional<Long> id ;

    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Date departureTime;
    private Date arrivalTime;
    private List<StopAirportRequest> stopAirports;

    public EditFlightDto(Optional<Long> id, Airport departureAirport, Airport arrivalAirport, Airline airline, Date departureTime, Date arrivalTime, List<StopAirportRequest> stopAirports) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.stopAirports = stopAirports;
    }
}

