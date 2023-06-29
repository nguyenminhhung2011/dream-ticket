package com.ticket.server.controller;

import com.ticket.server.dtos.FlightDtos.AddFlightDto;
import com.ticket.server.dtos.FlightDtos.EditFlightDto;
import com.ticket.server.dtos.FlightDtos.FlightDto;
import com.ticket.server.dtos.FlightDtos.FlightNotStopResponse;
import com.ticket.server.dtos.Payment.PaymentFlightTics;
import com.ticket.server.dtos.SimpleResponse;
import com.ticket.server.entities.Flight;
import com.ticket.server.service.IService.IFlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/flight")
@RestController
public class FlightController {
    private final IFlightService flightService;

    public FlightController( IFlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody FlightNotStopResponse addFlight(@RequestBody AddFlightDto addFlightDto){
        return flightService.addFlight(addFlightDto);
    }

    @GetMapping("/id={id}")
    public @ResponseBody FlightDto getFlight(@PathVariable Long id){
        return flightService.getFlight(id);
    }

    @GetMapping("/")
    public @ResponseBody List<FlightNotStopResponse> getAllFlight(){
        return flightService.getAllFlight();
    }
    @GetMapping("/filter/cursor={cursor}&pageSize={pageSize}")
    public @ResponseBody List<FlightNotStopResponse> filterFlight(
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
        );
    }

    @GetMapping("/departureId={id}")
    public  @ResponseBody List<FlightNotStopResponse> getFlightByDepartureId(
            @PathVariable("id")Integer id
            ) {
        return flightService.getFlightWithDepartureId(id);
    }
    @GetMapping("/arrivalId={id}")
    public  @ResponseBody List<FlightNotStopResponse> getFlightByArrivalId(
            @PathVariable("id")Integer id
            ) {
        return flightService.getFlightWithArrivalId(id);
    }
    @GetMapping("/airport={id}")
    public  @ResponseBody List<FlightNotStopResponse> getFlightByAirportId(
            @PathVariable("id")Integer id
            ) {
        return flightService.getFlightByAirportId(id);
    }

    @GetMapping("/payment={id}")
    public PaymentFlightTics getFlightTicsFromPayment(
            @PathVariable("id") long id
    ){
        return ResponseEntity.ok(flightService.getFlightTicsFromPayment(id)).getBody();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable Long id){

         flightService.deleteFlight(id);
    }

    @PatchMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody FlightNotStopResponse updateFlight(
            @PathVariable("id") Long id,
            @RequestBody EditFlightDto editFlightDto){
        return flightService.updateFlight(id, editFlightDto);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<FlightDto> searchFlights(
            @RequestParam(name = "departureKeyword") String departureKeyword,
            @RequestParam(name = "arrivalKeyword") String arrivalKeyword,
            @RequestParam(name = "arrivalTimeKeyword") String arrivalTimeKeyword) {
        return flightService.searchFlights(departureKeyword, arrivalKeyword, arrivalTimeKeyword);
    }

    @GetMapping("/sort")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<FlightDto> getFlightsSortedBy(
            @RequestParam(name = "sortBy") String sortBy,
            @RequestParam(name = "ascending", defaultValue = "true") boolean ascending) {
        return flightService.getFlightsSortedBy(sortBy, ascending);
    }
    @GetMapping("/page/cursor={cursor}&pageSize={pageSize}")
    public @ResponseBody SimpleResponse<FlightNotStopResponse> getFlightByPage(@PathVariable("cursor") int cursor, @PathVariable("pageSize") int pageSize){
        var flights =  flightService.getFlightByPage(cursor, pageSize);
        var totalPages = flightService.getPages(pageSize);
        return new SimpleResponse<FlightNotStopResponse>(cursor, pageSize, totalPages, flights);
    }



    private ResponseStatusException throwNotFoundException(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
