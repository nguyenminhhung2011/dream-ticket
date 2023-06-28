package com.ticket.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "airport")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name ="airportName" )
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

    @Column
    private String code;

    @OneToMany(mappedBy = "airport")
    private List<AirportImage> images;


    public Airport() {
    }

    public Airport(String airportName, String location, String imageUrl, String description, Long openTime, Long closeTime ,String code) {
        this.airportName = airportName;
        this.location = location;
        this.imageUrl = imageUrl;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.code =  code;
    }

    public Airport(String airportName, String location, String description, Long openTime, Long closeTime, String code ) {
        this.airportName = airportName;
        this.location = location;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.code =  code;
    }
}
