package com.ticket.server.repository;

import com.ticket.server.model.FlightClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightClassRepository extends JpaRepository<FlightClass, Long> {
}
