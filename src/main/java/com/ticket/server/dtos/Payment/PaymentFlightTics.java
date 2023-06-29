package com.ticket.server.dtos.Payment;

import com.ticket.server.dtos.FlightDtos.FlightDto;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.entities.PaymentEntity;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PaymentFlightTics {

    private Long id;
    private Long createdDate;
    private Double total;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    private FlightDto flight;
    private List<TicketDto> ticket;

    public static PaymentFlightTics fromEntity(PaymentEntity paymentEntity){
        return PaymentFlightTics
                .builder()
                .id(paymentEntity.getId())
                .total(paymentEntity.getTotal())
                .paymentStatus(paymentEntity.getStatus())
                .paymentType(paymentEntity.getPaymentType())
                .flight(new FlightDto(paymentEntity.getFlight()))
                .ticket(paymentEntity.getTicket().stream().map(TicketDto::fromEntity).toList())
                .build();
    }
}
