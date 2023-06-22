package com.ticket.server.service.IService;

import com.ticket.server.entities.Flight;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public interface IFlightService {
     Flight addFlight(Flight flight);

     Optional<Flight> getFlight(Long id);

     List<Flight> getAllFlight();

     void deleteFlight(Long id);

     Optional<Flight> updateFlight(Long id, Flight flight);

     List<Flight> searchFlights(String departureKeyword, String arrivalKeyword, String arrivalTimeKeyword) ;
     List<Flight> getFlightsSortedBy(String sortBy, boolean ascending) ;
     List<Flight> getFlightByPage(int cursor, int pageSize) ;
     Integer getPages(int pageSize);

     List<Flight> filterFlight(String locationDeparture,
                                      String locationArrival,
                                      String airlineName,
                                      Integer limit,
                                      Integer offset);

     List<Flight> getFlightWithArrivalId(Integer id);
     List<Flight> getFlightWithDepartureId(Integer id);
     List<Flight> getFlightByAirportId(Integer id);

}
