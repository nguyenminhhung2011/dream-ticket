package com.ticket.server.entities;


import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdDate;

    private Double total;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customers;

    @ManyToOne
    @JoinColumn(name =  "flight_id")
    private Flight flight;

    @OneToMany(mappedBy = "payment")
//    @JoinColumns(value = {
//            @JoinColumn(name = "customer_id",referencedColumnName = "customer_id"),
//            @JoinColumn(name = "flight_id",referencedColumnName = "flight_id")
//    })
    private List<TicketEntity> ticket;
}
