package com.ticket.server.controller;

import com.ticket.server.model.Airport;
import com.ticket.server.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
