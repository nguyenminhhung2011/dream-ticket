package com.ticket.server.repository;

import com.ticket.server.entities.Flight;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAll(Sort sort);
    List<Flight> findByDepartureAirportContainingOrArrivalAirportContainingAndArrivalTimeContaining(String departureKeyword, String arrivalKeyword, String arrivalTimeKeyword);
}
