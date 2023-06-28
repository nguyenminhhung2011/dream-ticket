package com.ticket.server.dtos.AirportDtos;

import com.ticket.server.entities.Airport;
import com.ticket.server.entities.AirportImage;
import lombok.Data;

import java.util.List;

@Data
public class AirportDto {
    private Long id;
    private String airportName;
    private String location;
    private  String imageUrl;
    private String description;
    private String  code;
    private Long openTime;
    private Long closeTime;


    public AirportDto(Airport airport){
        id = airport.getId();
        airportName = airport.getAirportName();
        imageUrl = airport.getImageUrl();
        location = airport.getLocation();
        description = airport.getDescription();
        openTime = airport.getOpenTime();
        closeTime = airport.getCloseTime();
        code = airport.getCode();
    }

}
