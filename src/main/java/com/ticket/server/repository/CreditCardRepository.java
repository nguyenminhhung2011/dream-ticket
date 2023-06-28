package com.ticket.server.repository;

import com.ticket.server.entities.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity,Long> {

    @Query(
            value = """
                select * from credit_card_entity as cr
                where cr.customer_id = :customerId;
            """
            ,nativeQuery = true
    )
    CreditCardEntity findAllByCustomerId(long customerId);
}
