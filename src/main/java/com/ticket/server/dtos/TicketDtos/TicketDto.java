package com.ticket.server.dtos.TicketDtos;

import com.ticket.server.entities.TicketEntity;
import com.ticket.server.enums.Gender;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {

    private long id;
    private String name;
    private String gender;
    private String phone;
    private String emailAddress;
    private int seat;
    private double price;
    private double luggage;
    private long birthday;
    private long timeBought;
    private int type;

    static public TicketDto fromEntity(TicketEntity entity){
        return TicketDto
                .builder()
                .id(entity.getId())
                .birthday(entity.getDob().getTime())
                .emailAddress(entity.getEmailAddress())
                .gender(entity.getGender().toString())
                .seat(entity.getSeat())
                .price(entity.getTicketInformation().getPrice())
                .timeBought(entity.getTimeBought().getTime())
                .phone(entity.getPhoneNumber())
                .luggage(entity.getLuggage())
                .name(entity.getName())
                .type(entity.getTicketInformation().getId().getTicketType())
                .build();
    }

    public TicketEntity toEntity(){
        return TicketEntity
                .builder()
                .id(getId())
                .dob(new Date(birthday))
                .emailAddress(getEmailAddress())
                .gender(Gender.valueOf(getGender()))
                .seat(getSeat())
                .timeBought(new Date(getTimeBought()))
                .phoneNumber(getPhone())
                .luggage(getLuggage())
                .name(getName())
                .build();
    }

}
