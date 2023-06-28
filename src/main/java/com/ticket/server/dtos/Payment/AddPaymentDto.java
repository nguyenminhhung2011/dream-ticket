package com.ticket.server.dtos.Payment;

import com.google.gson.annotations.JsonAdapter;
import com.ticket.server.dtos.CustomerDtos.CustomerCreditCardDto;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AddPaymentDto {
    private long id;
    private long createdDate;
    private double total;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    private List<TicketDto> ticket;
    private CustomerCreditCardDto customer;
}




