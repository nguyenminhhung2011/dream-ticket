package com.ticket.server.service;

import com.ticket.server.entities.Flight;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public interface IFlightService {
    public Flight addFlight(Flight flight);

    public Optional<Flight> getFlight(Long id);

    public List<Flight> getAllFlight();

    public void deleteFlight(Long id);

    public Optional<Flight> updateFlight(Long id, Flight flight);

    public List<Flight> searchFlights(String departureKeyword, String arrivalKeyword, String arrivalTimeKeyword) ;
    public List<Flight> getFlightsSortedBy(String sortBy, boolean ascending) ;
    public List<Flight> getFlightByPage(int cursor, int pageSize) ;
    public  Integer getPages(int pageSize);

    public  List<Flight> filterFlight(String locationDeparture,
                                      String locationArrival,
                                      String airlineName,
                                      Integer limit,
                                      Integer offset);


}
