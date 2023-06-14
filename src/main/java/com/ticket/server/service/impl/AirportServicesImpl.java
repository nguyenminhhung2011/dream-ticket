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
    public Optional<Airport> getAirport(Long id) {
        return airportRepository.findById(id);
    }

    @Override
    public List<Airport> getAllAirport() {
        return airportRepository.findAll();
    }

    @Override
    public void deleteAirport(Long id) {
        try {
            airportRepository.deleteById(id);
        }
        catch (Exception e){
            // do nothing
        }
    }

    @Override
    public Optional<Airport> updateAirport(Airport airport, Long id) {
        if(!airportRepository.existsById(id)){
            return Optional.empty();
        }
        airport.setId(id);
        airport.setAirportName(airport.getAirportName());
        airport.setLocation(airport.getLocation());
        airport.setImageUrl(airport.getImageUrl());
        airport.setDescription(airport.getDescription());
        airport.setCloseTime(airport.getCloseTime());
        airport.setOpenTime(airport.getOpenTime());

        return Optional.of(airportRepository.save(airport));
    }

    @Override
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }
}
