package com.ticket.server.service.impl;

import com.ticket.server.entities.Airport;
import com.ticket.server.repository.AirportRepository;
import com.ticket.server.service.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServicesImpl implements IAirportService {
    @Autowired
    private final AirportRepository airportRepository;

    public AirportServicesImpl(AirportRepository airportRepository) {
        this.airportRepository =  airportRepository;
    }
    @Override
    public List<Airport> getAirportByPage(int cursor, int pageSize) {
        PageRequest pageable = PageRequest.of(cursor, pageSize);
        Page<Airport> page = airportRepository.findAll(pageable);
        return page.getContent();
    }

    @Override
    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
//        return ResponseEntity.created(URI.create("/airport/" + createdAirport.getId())).body(createdAirport);
    }

    @Override
    public ResponseEntity<Airport> getAirport(Long id) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);

        return optionalAirport.map(airport -> ResponseEntity.ok().body(airport))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public List<Airport> getAllAirport() {
        return airportRepository.findAll();
    }

    @Override
    public ResponseEntity<Airport> deleteAirport(Long id) {
        try {
            airportRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Airport> updateAirport(Airport airport) {
        Optional<Airport> airportData = airportRepository.findById(airport.getId());
        if (airportData.isPresent()) {
            Airport updatedAirport = airportData.get();
            updatedAirport.setAirportName(airport.getAirportName());
            updatedAirport.setLocation(airport.getLocation());
            updatedAirport.setDescription(airport.getDescription());
            updatedAirport.setCloseTime(airport.getCloseTime());
            updatedAirport.setOpenTime(airport.getOpenTime());
            updatedAirport.setImageUrl(airport.getImageUrl());
            airportRepository.save(updatedAirport);
            return ResponseEntity.ok().body(updatedAirport);
        }
        return ResponseEntity.notFound().build();
    }
}
