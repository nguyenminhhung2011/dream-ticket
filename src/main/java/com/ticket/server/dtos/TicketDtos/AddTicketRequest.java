package com.ticket.server.dtos.TicketDtos;

import com.ticket.server.enums.Gender;
import com.ticket.server.enums.PaymentType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class AddTicketRequest {
    final List<TicketRequest> tickets;
    final Long flightId;
    final Long customerId;

    final String paymentType;
}


