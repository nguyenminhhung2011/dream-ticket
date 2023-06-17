package com.ticket.server.dtos.AirportDtos;

import com.ticket.server.entities.Airport;
import com.ticket.server.entities.AirportImage;

import java.util.List;

public class AirportDto {
    private Long id;
    private String airportName;
    private String location;
    private  String imageUrl;
    private String description;
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
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }


}
