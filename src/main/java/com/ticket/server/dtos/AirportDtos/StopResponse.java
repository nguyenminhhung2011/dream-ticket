package com.ticket.server.dtos.AirportDtos;

import com.ticket.server.entities.Airport;
import com.ticket.server.entities.StopAirport;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StopResponse {
    private Airport airport;
    private long stopTime;
    private String description;

    public StopResponse(StopAirport stop){
        this.airport = stop.getId().getAirport();
        this.stopTime = stop.getStopTime();
        this.description = stop.getDescription();
    }

}
