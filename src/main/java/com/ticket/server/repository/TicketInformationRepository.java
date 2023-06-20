package com.ticket.server.repository;

import com.ticket.server.entities.TicketInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketInformationRepository extends JpaRepository<TicketInformationEntity, Long> {
}
