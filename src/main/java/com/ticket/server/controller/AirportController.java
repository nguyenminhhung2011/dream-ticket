package com.ticket.server.controller;

import com.ticket.server.dtos.AirportDtos.AddAirportDto;
import com.ticket.server.dtos.AirportDtos.AirportDto;
import com.ticket.server.dtos.AirportDtos.EditAirportDto;
import com.ticket.server.dtos.SimpleResponse;
import com.ticket.server.entities.Airport;
import com.ticket.server.service.IService.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/api/v1/airport", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AirportController {
    private final IAirportService airportService;

    AirportController(@Autowired IAirportService airportService){
        this.airportService = airportService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody AirportDto addAirport(@RequestBody AddAirportDto addAirportDto){
        Airport airport = new Airport(addAirportDto.getAirportName(),
                addAirportDto.getLocation(),
                addAirportDto.getImageUrl(),
                addAirportDto.getDescription(),
                addAirportDto.getOpenTime(),
                addAirportDto.getCloseTime(),
                addAirportDto.getCode()
        );
        return new AirportDto(airportService.addAirport(airport));
    }

    @PatchMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody AirportDto updateAirport(
            @RequestBody EditAirportDto editAirportDto,
            @PathVariable("id") Long  id){
        Airport airport =  new Airport(editAirportDto.getAirportName(),
                editAirportDto.getLocation(),
                editAirportDto.getImageUrl(),
                editAirportDto.getDescription(),
                editAirportDto.getOpenTime(),
                editAirportDto.getCloseTime(),
                editAirportDto.getCode()
        );

        return airportService.updateAirport(airport, id)
                .map(AirportDto::new)
                .orElseThrow(this::throwNotFoundException);
    }
    @GetMapping(value = "/id={id}")
    public @ResponseBody AirportDto getAirport(@PathVariable("id") Long id){
        return airportService.getAirport(id)
                .map(AirportDto::new)
                .orElseThrow(this::throwNotFoundException);
    }

    @GetMapping("/page/cursor={cursor}&pageSize={pageSize}")
    public @ResponseBody SimpleResponse<AirportDto> getAirportByPage(@PathVariable("cursor") int cursor, @PathVariable("pageSize") int pageSize){
        var airports =  airportService.getAirportByPage(cursor, pageSize).stream()
                .map(AirportDto::new)
                .toList();
        var totalPages = airportService.getPages(pageSize);
        return new SimpleResponse<AirportDto>(cursor, pageSize, totalPages, airports);
    }

    @GetMapping("/")
    public @ResponseBody List<AirportDto> getAllAirport() {
        return airportService.getAllAirport().stream()
                .map(AirportDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/filter/keyword={keyword}")
    public @ResponseBody List<AirportDto> filterAirports(@PathVariable("keyword") String keyword){
        return  airportService.getAirportByFilter(keyword)
                .stream()
                .map(AirportDto::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirport(@PathVariable Long id){
        airportService.deleteAirport(id);
    }



    private ResponseStatusException throwNotFoundException(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
