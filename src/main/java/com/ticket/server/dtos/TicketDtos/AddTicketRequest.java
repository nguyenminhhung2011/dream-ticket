package com.ticket.server.dtos.TicketDtos;

import com.ticket.server.enums.Gender;
import com.ticket.server.enums.PaymentType;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class AddTicketRequest {
    final List<TicketRequest> tickets;
    final Long flightId;
    final PaymentType paymentType;
}


