package com.ticket.server.dtos.Payment;

import com.ticket.server.dtos.CustomerDtos.CustomerCreditCardDto;
import com.ticket.server.dtos.CustomerDtos.CustomerRawDto;
import com.ticket.server.dtos.FlightDtos.FlightDto;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.entities.PaymentEntity;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PaymentDtoDetail implements Serializable {
    private Long id;
    private Long createdDate;
    private Double total;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    private FlightDto flight;
    private List<TicketDto> ticket;
    private CustomerCreditCardDto customer;

    static public PaymentDtoDetail fromEntity(PaymentEntity paymentEntity){
        return PaymentDtoDetail
                .builder()
                .id(paymentEntity.getId())
                .createdDate(paymentEntity.getCreatedDate().getTime())
                .customer(CustomerCreditCardDto.fromEntity(paymentEntity.getCustomers()))
                .paymentStatus(paymentEntity.getStatus())
                .ticket(paymentEntity.getTicket().stream().map(TicketDto::fromEntity).toList())
                .total(paymentEntity.getTotal())
                .paymentType(paymentEntity.getPaymentType())
                .flight(new FlightDto(paymentEntity.getFlight()))
                .build();
    }
}
