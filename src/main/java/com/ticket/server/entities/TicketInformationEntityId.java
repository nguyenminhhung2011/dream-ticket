package com.ticket.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@Data
@RequiredArgsConstructor

@Embeddable
@AllArgsConstructor
public class TicketInformationEntityId implements Serializable {

    @Column(name = "ticket_type")
    private int ticketType;

    @ManyToOne
    @JoinColumn(name = "flight_id")
//    @Builder.Default
    private  Flight flight;


}
