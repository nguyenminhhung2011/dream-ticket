package com.ticket.server.repository;

import com.ticket.server.entities.Flight;
import com.ticket.server.entities.TicketInformationEntity;
import com.ticket.server.entities.TicketInformationEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TicketInformationRepository extends JpaRepository<TicketInformationEntity, TicketInformationEntityId> {
    @Query(value = """
                select t.* from ticket_information t
                where t.flight_id = :flight
            """,nativeQuery = true)
    List<TicketInformationEntity> findAllByFlight(Long flight);
    @Query(value = """
                select t.* from ticket_information t
                where t.flight_id = :flight
                and t.ticket_type = :ticketType
            """,nativeQuery = true)
    Optional<TicketInformationEntity> findByFlightAndType(Long flight,int ticketType);

    @Modifying
    @Query(value = """
            delete from ticket_information t where t.flight_id = :id
            """, nativeQuery = true)
    void deleteByFlightId(long id);

}
