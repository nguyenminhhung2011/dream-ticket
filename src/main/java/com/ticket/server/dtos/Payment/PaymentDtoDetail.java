package com.ticket.server.dtos.Payment;

import com.ticket.server.dtos.CustomerDtos.CustomerCreditCardDto;
import com.ticket.server.dtos.CustomerDtos.CustomerRawDto;
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
public class PaymentDtoDetail {
    private Long id;
    private Double total;
    private Long createdDate;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    private List<TicketDto> ticket;
    private CustomerCreditCardDto customers;

    static public PaymentDtoDetail fromEntity(PaymentEntity paymentEntity){
        return PaymentDtoDetail
                .builder()
                .id(paymentEntity.getId())
                .createdDate(paymentEntity.getCreatedDate().getTime())
                .customers(CustomerCreditCardDto.fromEntity(paymentEntity.getCustomers()))
                .paymentStatus(paymentEntity.getStatus())
                .ticket(paymentEntity.getTicket().stream().map(TicketDto::fromEntity).toList())
                .total(paymentEntity.getTotal())
                .paymentType(paymentEntity.getPaymentType())
                .build();
    }
}
