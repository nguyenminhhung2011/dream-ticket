package com.ticket.server.service;

import com.ticket.server.model.Airport;
import com.ticket.server.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Optional;

public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public ResponseEntity<Airport> addAirport(Airport airport){
        Airport createdAirport = airportRepository.save(airport);
        return ResponseEntity.created(URI.create("/airport/" + createdAirport.getId())).body(createdAirport);
    }

    public ResponseEntity<Airport> getAirport(Long id){
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        return optionalAirport.map(airport -> ResponseEntity.ok().body(airport))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
