package com.ticket.server.controller;

import com.ticket.server.model.Flight;
import com.ticket.server.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/flight")
@RestController
public class FlightController {
    @Autowired
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        return flightService.addFlight(flight);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long id){
        return flightService.getFlight(id);
    }

    @GetMapping("/")
    public List<Flight> getAllFlight(){
        return flightService.getAllFlight();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable Long id){
        return flightService.deleteFlight(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight){
        return flightService.updateFlight(id, flight);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam(name = "departureKeyword") String departureKeyword,
            @RequestParam(name = "arrivalKeyword") String arrivalKeyword,
            @RequestParam(name = "arrivalTimeKeyword") String arrivalTimeKeyword) {
        return flightService.searchFlights(departureKeyword, arrivalKeyword, arrivalTimeKeyword);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Flight>> getFlightsSortedBy(
            @RequestParam(name = "sortBy") String sortBy,
            @RequestParam(name = "ascending", defaultValue = "true") boolean ascending) {
        return flightService.getFlightsSortedBy(sortBy, ascending);
    }
}
