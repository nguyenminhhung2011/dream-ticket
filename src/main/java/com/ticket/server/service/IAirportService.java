package com.ticket.server.service;

import com.ticket.server.entities.Airport;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IAirportService {
    public List<Airport> getAirportByPage(int cursor, int pageSize) ;
    public List<Airport> getAllAirport();

    public Airport addAirport(Airport airport);
    public Optional<Airport> getAirport(Long id);
    public void deleteAirport(Long id);

    public Optional<Airport> updateAirport( Airport airport, Long id);

    public Airport saveAirport(Airport airport);

}
