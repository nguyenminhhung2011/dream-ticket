package com.ticket.server.dtos.TicketDtos;

import com.ticket.server.enums.Gender;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor

public class TicketRequest {
    private String name;
    private Gender gender;
    private String phoneNumber;
    private String emailAddress;
    private String seat;
    private Double luggage;
    private Long dob;
    private Long timeBought;
    private int ticketType;
}
