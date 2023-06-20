package com.ticket.server.dtos.TicketDtos;

import com.ticket.server.entities.TicketEntity;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Builder
public class TicketDto {
    private Long id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String emailAddress;
    private String seat;
    private Double luggage;
    private Long birthday;
    private Long timeBought;

    static public TicketDto fromEntity(TicketEntity entity){
        return TicketDto
                .builder()
                .id(entity.getId())
                .birthday(entity.getDob().getTime())
                .emailAddress(entity.getEmailAddress())
                .gender(entity.getGender().toString())
                .seat(entity.getSeat())
                .timeBought(entity.getTimeBought().getTime())
                .phoneNumber(entity.getPhoneNumber())
                .luggage(entity.getLuggage())
                .name(entity.getName())
                .build();
    }

}
