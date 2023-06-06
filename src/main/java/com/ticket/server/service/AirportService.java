package com.ticket.server.service;

import com.ticket.server.model.Airport;
import com.ticket.server.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }


    public ResponseEntity<Airport> addAirport(Airport airport){
        Airport createdAirport = airportRepository.save(airport);
        return ResponseEntity.created(URI.create("/airport/" + createdAirport.getId())).body(createdAirport);
    }

    public ResponseEntity<Airport> getAirport(Long id){
        Optional<Airport> optionalAirport = airportRepository.findById(id);

        return optionalAirport.map(airport -> ResponseEntity.ok().body(airport))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<Airport> getAllAirport(){
        return airportRepository.findAll();
    }

    public ResponseEntity<Airport> deleteAirport(Long id){
        try {
            airportRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Airport> updateAirport(Long id, Airport airport) {
        Optional<Airport> airportData = airportRepository.findById(id);

        if (airportData.isPresent()) {
            Airport updatedAirport = airportData.get();
            updatedAirport.setAirportName(airport.getAirportName());
            updatedAirport.setLocation(airport.getLocation());
            airportRepository.save(updatedAirport);
            return ResponseEntity.ok().body(updatedAirport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
