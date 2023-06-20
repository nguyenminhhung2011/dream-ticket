package com.ticket.server.service.impl;

import com.ticket.server.entities.Airport;
import com.ticket.server.entities.Flight;
import com.ticket.server.repository.FlightRepository;
import com.ticket.server.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServicesImpl implements IFlightService {

    @Autowired
    private  final FlightRepository flightRepository;

    public FlightServicesImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> getFlight(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public List<Flight> getAllFlight() {
        return flightRepository.findAll();
    }

    @Override
    public void deleteFlight(Long id) {
        try{
            flightRepository.deleteById(id);
        } catch (Exception e){
            // do nothing
        }
    }

    @Override
    public Optional<Flight> updateFlight(Long id, Flight flight) {
        if(!flightRepository.existsById(id)){
            return Optional.empty();
        }
        flight.setId(id);
        return Optional.of(flightRepository.save(flight));
    }

    @Override
    public List<Flight> searchFlights(String departureKeyword, String arrivalKeyword, String arrivalTimeKeyword) {
        return flightRepository.findByDepartureAirportContainingOrArrivalAirportContainingAndArrivalTimeContaining(departureKeyword, arrivalKeyword, arrivalTimeKeyword);
    }

    @Override
    public List<Flight> getFlightsSortedBy(String sortBy, boolean ascending) {
        Sort.Direction direction = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
        return flightRepository.findAll(pageable).getContent();

    }

    @Override
    public List<Flight> getFlightByPage(int cursor, int pageSize) {
        PageRequest pageable = PageRequest.of(cursor, pageSize);
        Page<Flight> page = flightRepository.findAll(pageable);
        return page.getContent();
    }

    @Override
    public Integer getPages(int pageSize) {
        long flightCount = flightRepository.count();
        return (Integer) (int) Math.ceil((double) flightCount/ pageSize);    }

    @Override
    public List<Flight> filterFlight(String locationArrival,
                                     String locationDeparture,
                                     String airlineName,
                                     Integer limit,
                                     Integer offset) {
        return flightRepository.filterFlight(locationArrival,
                                            locationDeparture,
                                            airlineName,
                                            limit,
                                            offset);
    }

    @Override
    public List<Flight> getFlightWithArrivalId(Integer id) {
        return flightRepository.getFlightWithArrivalId(id);
    }

    @Override
    public List<Flight> getFlightWithDepartureId(Integer id) {
        return flightRepository.getFlightDepartureId(id);
    }

    @Override
    public List<Flight> getFlightByAirportId(Integer id) {
        return flightRepository.getFlightByAirportId(id);
    }
}
