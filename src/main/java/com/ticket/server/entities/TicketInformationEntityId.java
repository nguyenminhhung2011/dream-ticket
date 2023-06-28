package com.ticket.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor

@Embeddable
@AllArgsConstructor
public class TicketInformationEntityId implements Serializable {

    @Column(name = "ticket_type")
    private int ticketType;
    @ManyToOne
    private  Flight flight;

}
