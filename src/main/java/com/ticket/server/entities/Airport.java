package com.ticket.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public List<AirportImage> getImages() {
        return images;
    }

    public void setImages(List<AirportImage> images) {
        this.images = images;
    }

    public void addImage(AirportImage image){
        images.add(image);
    }

    public void resetImage() {
        images.clear();
    }

    @Column(nullable = false)
    private String airportName;
    @Column(nullable = false)
    private String location;
    @Column
    private  String imageUrl;

    @Column
    private  String description;

    @Column
    private Long openTime;

    @Column
    private Long closeTime;

    @OneToMany(mappedBy = "airport")
    private List<AirportImage> images;

    public Airport() {
    }

    public Airport(String airportName, String location, String imageUrl, String description, Long openTime, Long closeTime) {
        this.airportName = airportName;
        this.location = location;
        this.imageUrl = imageUrl;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public Airport(String airportName, String location, String description, Long openTime, Long closeTime) {
        this.airportName = airportName;
        this.location = location;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
