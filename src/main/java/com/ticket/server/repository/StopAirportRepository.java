package com.ticket.server.repository;

import com.ticket.server.entities.StopAirport;
import com.ticket.server.entities.StopAirportId;
import com.ticket.server.entities.TicketInformationEntity;
import com.ticket.server.entities.TicketInformationEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StopAirportRepository extends JpaRepository<StopAirport, StopAirportId> {
    @Query(value = """
                select t.* from stop_airport t
                where t.flight_id = :flight
            """, nativeQuery = true)
    List<StopAirport> findAllByFlight(long flight);

}
