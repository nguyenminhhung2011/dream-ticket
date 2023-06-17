package com.ticket.server.repository;

import com.ticket.server.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    //🐼Update after with filter
    @Query( value = "SELECT p.* FROM airport p WHERE p.airport_name LIKE %?1%", nativeQuery = true)
    public List<Airport> findByFilter(String keyword);
}
