package com.ticket.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ticket.server.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ticket_entity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private int seat;

    @Column(nullable = false)
    private Double luggage;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false)
    private Date timeBought;

    @ManyToOne
    @JoinColumns(
        value = {
            @JoinColumn(name = "flight_id", referencedColumnName = "flight_id"),
            @JoinColumn(name = "type", referencedColumnName = "ticket_type")
        }
    )
    private TicketInformationEntity ticketInformation;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;


    //    @ManyToOne
    //    @JoinColumn(name = "flight_id")
    //    private Flight flight;
    //

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

}

