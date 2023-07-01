package com.ticket.server.repository;

import com.ticket.server.dtos.OverViewDto.OverviewProjection;
import com.ticket.server.entities.Airport;
import com.ticket.server.entities.Flight;
import com.ticket.server.entities.TotalByDateRange;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
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
                WHERE f.arrival_airport_id = :id
                   OR f.departure_airport_id = :id
    """, nativeQuery = true )
    List<Flight> getFlightByAirportId(Integer id);

    @Query(value = """
            SELECT f.*
            FROM flight AS f
            JOIN airport AS a1 ON f.arrival_airport_id = a1.id
            WHERE a1.id = :id
            ORDER BY f.arrival_time
            """, nativeQuery = true)
    List<Flight> getFlightWithArrivalId(Integer id);

    @Query(value = """
            SELECT f.*
            FROM flight AS f
            JOIN airport AS a1 ON f.departure_airport_id = a1.id
            WHERE a1.id = :id
            ORDER BY f.departure_airport_id
            """, nativeQuery = true)
    List<Flight> getFlightDepartureId(Integer id);
   @Query(value = """
           SELECT f.*
           FROM flight AS f
           WHERE MONTH(f.departure_time) = :month AND YEAR(f.departure_time) = :year AND DAY(f.departure_time) = :day
            """, nativeQuery = true)
    List<Flight> getFlightByDate(int day, int month , int year);

    @Query(value = """
            select count(*) from flight f
           where DATEDIFF(f.departure_time,:date)  = 0;
            """, nativeQuery = true
    )
    long countFlightByDate(Date date);

    @Query(
            value = """
                select fl.departure_time as date ,count(distinct fl.id) as total
                from flight fl
                where datediff(fl.departure_time,:from) >= 0 
                and datediff(fl.departure_time,:to) <= 0
                group by fl.departure_time;
            """
            ,nativeQuery = true
    )
    List<Tuple> countFlightByDateRange(Date from, Date to);
}

