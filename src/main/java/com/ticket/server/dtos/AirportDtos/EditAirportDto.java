package com.ticket.server.dtos.AirportDtos;

import java.util.Optional;

public class EditAirportDto {
    public Optional<Long> getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = Optional.of(id);
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }
    private Optional<Long> id ;
    private String airportName;
    private String location;

    private String description;

    private String imageUrl;
    private Long openTime;
    private Long closeTime;

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
