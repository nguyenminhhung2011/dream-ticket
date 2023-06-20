package com.ticket.server.repository;

import com.ticket.server.entities.TicketInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketInformationRepository extends JpaRepository<TicketInformation, Long> {
}
