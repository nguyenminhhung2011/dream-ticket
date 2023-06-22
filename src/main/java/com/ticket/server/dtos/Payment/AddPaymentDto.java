package com.ticket.server.dtos.Payment;

import com.ticket.server.entities.CustomerEntity;
import com.ticket.server.entities.TicketEntity;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AddPaymentDto {
    private Long createdDate;
    private Double total;
    private PaymentStatus status;
    private PaymentType paymentType;
    private CustomerEntity customers;
    private List<TicketEntity> ticket;
}
