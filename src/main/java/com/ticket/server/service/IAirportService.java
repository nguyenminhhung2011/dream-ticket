package com.ticket.server.service;

import com.ticket.server.entities.Airport;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAirportService {
    public List<Airport> getAirportByPage(int cursor, int pageSize) ;
    public Airport addAirport(Airport airport);

    public ResponseEntity<Airport> getAirport(Long id);
    public List<Airport> getAllAirport();
    public ResponseEntity<Airport> deleteAirport(Long id);

    public ResponseEntity<Airport> updateAirport( Airport airport);

}
