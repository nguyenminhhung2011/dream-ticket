package com.ticket.server.controller;

import com.ticket.server.dtos.AirportDtos.AirportDto;
import com.ticket.server.dtos.FlightDtos.AddFlightDto;
import com.ticket.server.dtos.FlightDtos.EditFlightDto;
import com.ticket.server.dtos.FlightDtos.FlightDto;
import com.ticket.server.dtos.SimpleResponse;
import com.ticket.server.entities.Flight;
import com.ticket.server.service.IFlightService;
import org.hibernate.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/flight")
@RestController
public class FlightController {
    private final IFlightService flightService;

    public FlightController(@Autowired IFlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody FlightDto addFlight(@RequestBody AddFlightDto addFlightDto){
        Flight flight = new Flight(
                addFlightDto.getDepartureAirport(),
                addFlightDto.getArrivalAirport(),
                addFlightDto.getAirline(),
                addFlightDto.getDepartureTime(),
                addFlightDto.getArrivalTime()
        );
        return new FlightDto(flightService.addFlight(flight));
    }

    @GetMapping("/id={id}")
    public @ResponseBody FlightDto getFlight(@PathVariable Long id){
        return flightService.getFlight(id)
                .map(FlightDto::new)
                .orElseThrow(this::throwNotFoundException);
    }

    @GetMapping("/")
    public @ResponseBody List<FlightDto> getAllFlight(){
        return flightService.getAllFlight()
                .stream()
                .map(FlightDto::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/filter/cursor={cursor}&pageSize={pageSize}")
    public @ResponseBody List<FlightDto> filterFlight(
            @RequestParam(name = "locationArrival") String locationArrival,
            @RequestParam(name= "locationDeparture") String locationDeparture,
            @RequestParam(name = "airlineName") String airlineName,
            @PathVariable("cursor") Integer cursor,
            @PathVariable("pageSize") Integer pageSize
    ){

        return flightService.filterFlight(
                locationArrival,
                locationDeparture,
                airlineName,
                pageSize,
                cursor * pageSize
        ).stream().map(FlightDto::new).collect(Collectors.toList());
    }

    @GetMapping("/departureId={id}")
    public  @ResponseBody List<FlightDto> getFlightByDepartureId(
            @PathVariable("id")Integer id
            ) {
        return flightService.getFlightWithDepartureId(id)
                .stream()
                .map(FlightDto::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/arrivalId={id}")
    public  @ResponseBody List<FlightDto> getFlightByArrivalId(
            @PathVariable("id")Integer id
            ) {
        return flightService.getFlightWithArrivalId(id)
                .stream()
                .map(FlightDto::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/airport={id}")
    public  @ResponseBody List<FlightDto> getFlightByAirportId(
            @PathVariable("id")Integer id
            ) {
        return flightService.getFlightByAirportId(id)
                .stream()
                .map(FlightDto::new)
                .collect(Collectors.toList());
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable Long id){

         flightService.deleteFlight(id);
    }

    @PatchMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody FlightDto updateFlight(
            @PathVariable("id") Long id,
            @RequestBody EditFlightDto editFlightDto){
        Flight flight = new Flight(
                editFlightDto.getDepartureAirport(),
                editFlightDto.getArrivalAirport(),
                editFlightDto.getAirline(),
                editFlightDto.getDepartureTime(),
                editFlightDto.getArrivalTime()
        );
        return flightService.updateFlight(id, flight)
                .map(FlightDto::new)
                .orElseThrow(this::throwNotFoundException);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<FlightDto> searchFlights(
            @RequestParam(name = "departureKeyword") String departureKeyword,
            @RequestParam(name = "arrivalKeyword") String arrivalKeyword,
            @RequestParam(name = "arrivalTimeKeyword") String arrivalTimeKeyword) {
        return flightService.searchFlights(departureKeyword, arrivalKeyword, arrivalTimeKeyword)
                .stream()
                .map(FlightDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/sort")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<FlightDto> getFlightsSortedBy(
            @RequestParam(name = "sortBy") String sortBy,
            @RequestParam(name = "ascending", defaultValue = "true") boolean ascending) {
        return flightService.getFlightsSortedBy(sortBy, ascending)
                .stream()
                .map(FlightDto::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/page/cursor={cursor}&pageSize={pageSize}")
    public @ResponseBody SimpleResponse<FlightDto> getFlightByPage(@PathVariable("cursor") int cursor, @PathVariable("pageSize") int pageSize){
        var flights =  flightService.getFlightByPage(cursor, pageSize).stream()
                .map(FlightDto::new)
                .toList();
        var totalPages = flightService.getPages(pageSize);
        return new SimpleResponse<FlightDto>(cursor, pageSize, totalPages, flights);
    }



    private ResponseStatusException throwNotFoundException(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
