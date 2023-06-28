package com.ticket.server.dtos.AirportDtos;

import com.ticket.server.entities.Airport;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StopAirportRequest {
    private Airport airport;
    private long stopTime;
    private String description;
}
