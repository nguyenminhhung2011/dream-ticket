package com.ticket.server.model;

import com.ticket.server.entities.Flight;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String identityCard;
    private String phone;

    @ManyToOne
    private Flight flight;
}
