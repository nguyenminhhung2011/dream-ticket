package com.ticket.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ticket.server.enums.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ticket_entity")
@Data
@Builder
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false,length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String seat;

    @Column(nullable = false)
    private Double luggage;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false)
    private Date timeBought;

    @ManyToOne
    @JoinColumn(name = "ticketInformation")
    private TicketInformationEntity ticketInformation;

    @ManyToOne
    @JoinColumn(name = "payment_entity")
    private PaymentEntity payment;
}

