package com.ticket.server.dtos.AirportDtos;

import lombok.Data;

import java.util.Optional;

@Data
public class EditAirportDto {

    private Optional<Long> id ;
    private String airportName;
    private String location;

    private String description;

    private String imageUrl;
    private Long openTime;
    private Long closeTime;

    private  String code;
    public EditAirportDto(Long id, String airportName, String location, String description,String imageUrl, Long openTime, Long closeTime) {
        this.id = Optional.of(id);
        this.airportName = airportName;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
