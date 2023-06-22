package com.ticket.server.repository;

import com.ticket.server.dtos.Payment.PaymentFilter;
import com.ticket.server.entities.PaymentEntity;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    @Query(value = """
            SELECT p.*
            FROM payment_entity AS p
            WHERE p.payment_type LIKE %:paymentType%
            AND p.status LIKE %:paymentStatus%
            AND p.created_date = :createDate
            LIMIT :limit OFFSET :offset
    """, nativeQuery = true )
    List<PaymentEntity> filterPayment(
            int offset,
            long limit,
            long createDate,
            String paymentStatus,
            String paymentType
    );
}
