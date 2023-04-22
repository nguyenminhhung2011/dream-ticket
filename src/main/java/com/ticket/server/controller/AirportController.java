package com.ticket.server.controller;

import com.ticket.server.model.Airport;
import com.ticket.server.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/airport")
@RestController
public class AirportController {
    @Autowired
    private AirportService airportService;

    @PostMapping("/add")
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport){
        return airportService.addAirport(airport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirport(@PathVariable Long id){
        return airportService.getAirport(id);
    }

    @GetMapping("/")
    public List<Airport> getAllAirport() {
        return airportService.getAllAirport();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Airport> deleteAirport(@PathVariable Long id){
        return airportService.deleteAirport(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport){
        return airportService.updateAirport(id, airport);
    }

}
