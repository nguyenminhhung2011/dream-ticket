package com.ticket.server.dtos.FlightDtos;

import com.ticket.server.entities.Airline;
import com.ticket.server.entities.Airport;

import java.util.Date;
import java.util.Optional;

public class EditFlightDto {
    private Optional<Long> id ;

    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Date departureTime;
    private Date arrivalTime;

    public EditFlightDto(Optional<Long> id, Airport departureAirport, Airport arrivalAirport, Airline airline, Date departureTime, Date arrivalTime) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Optional<Long> getId() {
        return id;
    }

    public void setId(Optional<Long> id) {
        this.id = id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
