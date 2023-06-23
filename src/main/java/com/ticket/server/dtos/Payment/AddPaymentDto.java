package com.ticket.server.dtos.Payment;

import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddPaymentDto {
    private Long createdDate;
    private Double total;
    private PaymentStatus status;
    private PaymentType paymentType;
    private String customerId;
    private String flightId;


}

