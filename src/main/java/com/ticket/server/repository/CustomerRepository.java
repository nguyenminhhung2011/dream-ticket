package com.ticket.server.repository;

import com.ticket.server.entities.Airport;
import com.ticket.server.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    @Query( value = "SELECT p.* FROM customer_entity p WHERE p.name LIKE %:keyword%", nativeQuery = true)
    List<CustomerEntity> searchCustomer(String keyword);
}
