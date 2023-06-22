package com.ticket.server.dtos.TicInformationDto;

import com.ticket.server.entities.TicketInformationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddTicInformationRequest {
    final List<TicketInformationEntity> ticketInformation;
    final Long flightId;

}
