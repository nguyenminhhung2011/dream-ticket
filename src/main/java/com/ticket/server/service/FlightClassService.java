package com.ticket.server.service;

import com.ticket.server.model.FlightClass;
import com.ticket.server.repository.FlightClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class FlightClassService {
    @Autowired
    private final FlightClassRepository flightClassRepository;


    public FlightClassService(FlightClassRepository flightClassRepository) {
        this.flightClassRepository = flightClassRepository;
    }

    public ResponseEntity<FlightClass> addFlightClass(FlightClass flightClass){
        FlightClass createdFlightClass = flightClassRepository.save(flightClass);
        return ResponseEntity.created(URI.create("/flightClass/" + createdFlightClass.getId())).body(createdFlightClass);
    }

    public ResponseEntity<FlightClass> getFlightClass(Long id){
        Optional<FlightClass> optionalFlight = flightClassRepository.findById(id);
        return optionalFlight.map(flight -> ResponseEntity.ok().body(flight))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<FlightClass> getAllFlightClass(){
        return flightClassRepository.findAll();
    }

    public ResponseEntity<FlightClass> deleteFlightClass(Long id){
        Optional<FlightClass> optionalFlightClass = flightClassRepository.findById(id);
        if(optionalFlightClass.isPresent()){
            flightClassRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<FlightClass> updateFlightClass(Long id, FlightClass flightClass){
        Optional<FlightClass> optionalFlightClass = flightClassRepository.findById(id);
        if(optionalFlightClass.isPresent()){
            FlightClass updatedFlightClass = optionalFlightClass.get();
            updatedFlightClass.setName(flightClass.getName());
            updatedFlightClass.setFlightClassSeatsList(flightClass.getFlightClassSeatsList());

            flightClassRepository.save(updatedFlightClass);
            return ResponseEntity.ok().body(updatedFlightClass);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
