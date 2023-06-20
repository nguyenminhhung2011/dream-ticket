package com.ticket.server.entities;


import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long createdDate;
    private Double total;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customers;

    @OneToMany(mappedBy = "ticket_entity")
    private List<TicketEntity> ticket;
}
