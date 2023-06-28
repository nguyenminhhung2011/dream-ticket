package com.ticket.server.dtos.TicketDtos;

import com.ticket.server.enums.Gender;
import com.ticket.server.enums.TicketType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TicketRequest {
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phone;
    private String emailAddress;
    private Double luggage;
    private Long birthday;
    private Long timeBought;
    private int type;
    private int seat;
}

