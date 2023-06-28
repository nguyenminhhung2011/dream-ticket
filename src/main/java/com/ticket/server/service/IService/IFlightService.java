package com.ticket.server.service.IService;

import com.ticket.server.dtos.FlightDtos.AddFlightDto;
import com.ticket.server.dtos.FlightDtos.EditFlightDto;
import com.ticket.server.dtos.FlightDtos.FlightDto;
import com.ticket.server.dtos.FlightDtos.FlightNotStopResponse;
import com.ticket.server.entities.Flight;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public interface IFlightService {
     FlightNotStopResponse addFlight(AddFlightDto flight);

     FlightDto getFlight(Long id);

     List<FlightNotStopResponse> getAllFlight();

     void deleteFlight(Long id);

     FlightNotStopResponse updateFlight(Long id, EditFlightDto flight);

     List<FlightDto> searchFlights(String departureKeyword, String arrivalKeyword, String arrivalTimeKeyword) ;
     List<FlightDto> getFlightsSortedBy(String sortBy, boolean ascending) ;
     List<FlightNotStopResponse> getFlightByPage(int cursor, int pageSize) ;
     Integer getPages(int pageSize);

     List<FlightNotStopResponse> filterFlight(String locationDeparture,
                                      String locationArrival,
                                      String airlineName,
                                      Integer limit,
                                      Integer offset);

     List<FlightNotStopResponse> getFlightWithArrivalId(Integer id);
     List<FlightNotStopResponse> getFlightWithDepartureId(Integer id);
     List<FlightNotStopResponse> getFlightByAirportId(Integer id);

}
