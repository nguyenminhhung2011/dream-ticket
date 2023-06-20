package com.ticket.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ticket_information")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TicketInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int ticketType;

    @OneToMany
    @JoinColumn(name = "ticket_id")
    private List<TicketEntity> ticket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTicketType() {
        return ticketType;
    }

    public void setTicketType(int ticketType) {
        this.ticketType = ticketType;
    }

    public List<TicketEntity> getTicket() {
        return ticket;
    }

    public void setTicket(List<TicketEntity> ticket) {
        this.ticket = ticket;
    }

    public TicketInformation() {
    }

    public TicketInformation(Long id, Flight flight, int quantity, double price, int ticketType, List<TicketEntity> ticket) {
        this.id = id;
        this.flight = flight;
        this.quantity = quantity;
        this.price = price;
        this.ticketType = ticketType;
        this.ticket = ticket;
    }
}
