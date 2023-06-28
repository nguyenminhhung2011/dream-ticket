package com.ticket.server.controller;

import com.ticket.server.model.FlightClass;
import com.ticket.server.service.FlightClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flightclass")
public class FlightClassController {
    @Autowired
    private final FlightClassService flightClassService;

    public FlightClassController(FlightClassService flightClassService) {
        this.flightClassService = flightClassService;
    }

    @PostMapping("/add")
    public ResponseEntity<FlightClass> addFlightClass(@RequestBody FlightClass flightClass){
        return flightClassService.addFlightClass(flightClass);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightClass> getFlight(@PathVariable Long id){
        return flightClassService.getFlightClass(id);
    }

    @GetMapping("/")
    public List<FlightClass> getAllFlightClass(){
        return flightClassService.getAllFlightClass();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightClass> updateFlightClass(@PathVariable Long id, @RequestBody FlightClass flightClass){
        return flightClassService.updateFlightClass(id, flightClass);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightClass> deleteFligtClass(@PathVariable Long id){
        return flightClassService.deleteFlightClass(id);
    }
}
