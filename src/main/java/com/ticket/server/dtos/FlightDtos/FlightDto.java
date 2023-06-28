package com.ticket.server.dtos.FlightDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ticket.server.dtos.AirportDtos.StopResponse;
import com.ticket.server.entities.Airline;
import com.ticket.server.entities.Airport;
import com.ticket.server.entities.Flight;
import com.ticket.server.entities.StopAirport;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FlightDto {
    private Long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Date departureTime;
    private Date arrivalTime;

    private List<StopResponse> stopAirports;

    public FlightDto(Long id, Airport departureAirport, Airport arrivalAirport, Airline airline, Date departureTime, Date arrivalTime,List<StopResponse> stopAirports ) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.stopAirports = stopAirports;
    }

    public  FlightDto(Flight flight){
        id = flight.getId();
        departureAirport = flight.getDepartureAirport();
        arrivalAirport = flight.getArrivalAirport();
        departureTime = flight.getDepartureTime();
        arrivalTime = flight.getArrivalTime();
        airline = flight.getAirline();
    }
    public  FlightDto(Flight flight, List<StopResponse> stops){
        id = flight.getId();
        departureAirport = flight.getDepartureAirport();
        arrivalAirport = flight.getArrivalAirport();
        departureTime = flight.getDepartureTime();
        arrivalTime = flight.getArrivalTime();
        airline = flight.getAirline();
        stopAirports = stops;
    }

    @Override
    public String toString() {
        return "FlightDto{" +
                "id=" + id +
                ", departureAirport=" + departureAirport.getLocation() +
                ", arrivalAirport=" + arrivalAirport.getLocation() +
                ", airline=" + airline.getAirlineName() +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
