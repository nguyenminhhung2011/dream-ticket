package com.ticket.server.controller;

import com.ticket.server.dtos.Airline.AirlineDto;
import com.ticket.server.service.IService.IAirlineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/api/v1/airline", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AirlineController {
    private final IAirlineService airlineService;
    public AirlineController(IAirlineService airlineService){
        this.airlineService = airlineService;
    }
    @GetMapping("/")
    public @ResponseBody List<AirlineDto> getAllAirport() {
        return airlineService.getAllAirline().stream()
                .map(AirlineDto::new)
                .collect(Collectors.toList());
    }
    private ResponseStatusException throwNotFoundException(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
