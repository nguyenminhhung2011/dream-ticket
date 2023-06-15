package com.ticket.server.dtos.AirportDtos;

import com.ticket.server.entities.AirportImage;

import java.util.List;

public class AddAirportDto {
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
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }




    private String airportName;
    private String location;

    private String description;

    private  String imageUrl;
    private Long openTime;
    private Long closeTime;

    public AddAirportDto(String airportName, String location, String description, String imageUrl, Long openTime, Long closeTime) {
        this.airportName = airportName;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
