package com.ticket.server.repository;

import com.ticket.server.model.FlightClassSeats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightClassSeatsRepository extends JpaRepository<FlightClassSeats, Long> {
}
