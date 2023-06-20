package com.ticket.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ticket_entity")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String emailAddress;
    private int sheet;
    private int luggage;
    private Date dateBorn;
    private Date timeBought;

    @ManyToOne
    @JoinColumn(name = "ticketInformation")
    private TicketInformation ticketInformation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getSheet() {
        return sheet;
    }

    public void setSheet(int sheet) {
        this.sheet = sheet;
    }

    public int getLuggage() {
        return luggage;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }

    public Date getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }

    public Date getTimeBought() {
        return timeBought;
    }

    public void setTimeBought(Date timeBought) {
        this.timeBought = timeBought;
    }

    public TicketInformation getTicketInformation() {
        return ticketInformation;
    }

    public void setTicketInformation(TicketInformation ticketInformation) {
        this.ticketInformation = ticketInformation;
    }

    public TicketEntity() {
    }

    public TicketEntity(Long id, String name, String gender, String phoneNumber, String emailAddress, int sheet, int luggage, Date dateBorn, Date timeBought, TicketInformation ticketInformation) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.sheet = sheet;
        this.luggage = luggage;
        this.dateBorn = dateBorn;
        this.timeBought = timeBought;
        this.ticketInformation = ticketInformation;
    }
}
