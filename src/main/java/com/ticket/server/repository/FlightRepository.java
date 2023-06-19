package com.ticket.server.repository;

import com.ticket.server.entities.Airport;
import com.ticket.server.entities.Flight;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAll(Sort sort);
    List<Flight> findByDepartureAirportContainingOrArrivalAirportContainingAndArrivalTimeContaining(String departureKeyword, String arrivalKeyword, String arrivalTimeKeyword);

    @Query(value = """
            SELECT f.*
            FROM flight AS f
            JOIN airport AS a1 ON f.arrival_airport_id = a1.id
            JOIN airport AS a2 ON f.departure_airport_id = a2.id
            JOIN airline AS airline ON f.airline_id = airline.id
            WHERE a1.location LIKE %:locationArrival%
                OR a2.location LIKE %:locationDeparture%
                AND airline.airline_name = :airlineName
            LIMIT :limit OFFSET :offset
    """, nativeQuery = true )
    List<Flight> filterFlight(String locationArrival,
                              String locationDeparture,
                              String airlineName,
                              Integer limit,
                              Integer offset);

    @Query(value = """
            SELECT f.*
            FROM flight AS f
            JOIN airport AS a1 ON f.arrival_airport_id = a1.id
            	where a1.id = :id
            ORDER BY f.arrival_time
            """, nativeQuery = true)
    List<Flight> getFlightWithArrivalId(Integer id);

    @Query(value = """
            SELECT f.*
            FROM flight AS f
            JOIN airport AS a1 ON f.departure_airport_id = a1.id
            	where a1.id = :id
            ORDER BY f.departure_airport_id
            """, nativeQuery = true)
    List<Flight> getFlightDepartureId(Integer id);
}

//            LIMIT ?4 OFFSET ?5,
