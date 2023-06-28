package com.ticket.server.dtos.Airline;

import com.ticket.server.entities.Airline;
import com.ticket.server.entities.Airport;

public class AirlineDto {
    private Long id;
    private String airlineName;

    public  AirlineDto(){}
    public AirlineDto(Long id, String airlineName) {
        this.id = id;
        this.airlineName = airlineName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
    public AirlineDto(Airline airline){
        id = airline.getId();
       airlineName = airline.getAirlineName();
    }
}
