package com.ticket.server.dtos.TicketDtos;

import com.ticket.server.enums.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class AddTicketRequest {
    private String name;
    private Gender gender;
    private String phoneNumber;
    private String emailAddress;
    private String seat;
    private Double luggage;
    private Long dob;
    private Long timeBought;
}
