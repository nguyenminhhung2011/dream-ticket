package com.ticket.server.repository;

import com.ticket.server.entities.Flight;
import com.ticket.server.entities.TicketInformationEntity;
import com.ticket.server.entities.TicketInformationEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketInformationRepository extends JpaRepository<TicketInformationEntity, TicketInformationEntityId> {
//    Optional<List<TicketInformationEntity>> findAllByTicketTypeAndFlight(int ticketType, Flight flight);
}
