package com.ticket.server.repository;

import com.ticket.server.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    @Query(
            value = """
                select* from payment_entity pa
                where datediff(pa.created_date ,:time) = 0
            """
            ,nativeQuery = true
    )
    List<PaymentEntity> findByCreatedDate(Date time);

    @Query(
            value = """
                select pa.* from payment_entity pa
                join customer_entity cus 
                on cus.id = pa.customer_id
                join flight fl 
                on fl.id = pa.flight_id
                where cus.name like %:keyword%
            """
            ,nativeQuery = true
    )
    List<PaymentEntity> searchPayment(String keyword);

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

    @Query(
            value = """
                select* from payment_entity pa
                where pa.customer_id = :id
                order by created_date
                limit 1;
            """
            ,nativeQuery = true
    )
    Optional<PaymentEntity> getPaymentByCustomerIdAndLatestCreateDate(long id);

    @Query(
            value = """
            select * from payment_entity pa
            where pa.customer_id = :id
            """,
            nativeQuery = true
    )
    List<PaymentEntity> findByCustomerId(long id);

    @Query(
            value = """
            select * from payment_entity pa
            where pa.flight_id = :id
            """,
            nativeQuery = true
    )
    List<PaymentEntity> findByFlightId(long id);


    @Query(
            value = """
               select count(distinct pa.customer_id)
               from payment_entity pa
               where pa.created_date = :time
            """
            ,nativeQuery = true)
    long countCustomerByDate(Date time);
}
