package com.ticket.server.dtos.AirportDtos;

import com.ticket.server.entities.AirportImage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddAirportDto {


    private String airportName;
    private String location;

    private String description;

    private  String imageUrl;
    private Long openTime;
    private Long closeTime;

    private String code;


}
