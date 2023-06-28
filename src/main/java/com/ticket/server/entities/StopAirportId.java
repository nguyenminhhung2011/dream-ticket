package com.ticket.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Embeddable
@AllArgsConstructor
public class StopAirportId implements Serializable {
    @ManyToOne
    private Flight flight;

    @ManyToOne
    private  Airport airport;
}
