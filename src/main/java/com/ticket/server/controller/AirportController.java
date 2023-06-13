package com.ticket.server.controller;

import com.ticket.server.dtos.AirportDtos.AddAirportDto;
import com.ticket.server.dtos.AirportDtos.AirportDto;
import com.ticket.server.entities.Airport;
import com.ticket.server.service.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping(value = "/api/v1/airport", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AirportController {
    private final IAirportService airportService;

    AirportController(@Autowired IAirportService airportService){
        this.airportService = airportService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport){
//        return airportService.addAirport(airport);
//    }
    public @ResponseBody AirportDto addAirport(@RequestBody AddAirportDto addAirportDto){
        Airport airport = new Airport(addAirportDto.getAirportName(),
                addAirportDto.getLocation(),
                addAirportDto.getImageUrl(),
                addAirportDto.getDescription(),
                addAirportDto.getOpenTime(),
                addAirportDto.getCloseTime()
        );
        return new AirportDto(airportService.addAirport(airport));
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
    public @ResponseBody List<AirportDto> getAllAirport() {
        return airportService.getAllAirport().stream()
                .map(AirportDto::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Airport> deleteAirport(@PathVariable Long id){
        return airportService.deleteAirport(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Airport> updateAirport(@RequestBody Airport airport){
        return airportService.updateAirport(airport);
    }

    private ResponseStatusException throwNotFoundException(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
