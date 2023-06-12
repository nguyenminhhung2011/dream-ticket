package com.ticket.server.controller;

import com.ticket.server.model.Airport;
import com.ticket.server.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/airport")
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
    @GetMapping("/page/{cursor}/{pageSize}")
    public List<Airport> getAirportByPage(@PathVariable int cursor, @PathVariable int pageSize){
        return airportService.getAirportByPage(cursor, pageSize);
    }

    @GetMapping("/")
    public List<Airport> getAllAirport() {
        return airportService.getAllAirport();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Airport> deleteAirport(@PathVariable Long id){
        return airportService.deleteAirport(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Airport> updateAirport(@RequestBody Airport airport){
        return airportService.updateAirport(airport);
    }

}
