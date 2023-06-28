package com.ticket.server.dtos.FlightDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ticket.server.entities.Airline;
import com.ticket.server.entities.Airport;
import com.ticket.server.entities.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightNotStopResponse {
    private Long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Date departureTime;
    private Date arrivalTime;
    public  FlightNotStopResponse(Flight flight){
        id = flight.getId();
        departureAirport = flight.getDepartureAirport();
        arrivalAirport = flight.getArrivalAirport();
        departureTime = flight.getDepartureTime();
        arrivalTime = flight.getArrivalTime();
        airline = flight.getAirline();
    }
}
