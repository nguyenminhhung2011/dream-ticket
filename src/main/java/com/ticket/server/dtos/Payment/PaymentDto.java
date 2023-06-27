package com.ticket.server.dtos.Payment;

import com.ticket.server.dtos.CustomerDtos.CustomerRawDto;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.entities.CustomerEntity;
import com.ticket.server.entities.PaymentEntity;
import com.ticket.server.entities.TicketEntity;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PaymentDto implements Serializable {
    private Long id;
    private Long createdDate;
    private Double total;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    private List<TicketDto> ticket;
    private CustomerRawDto customer;

    public static PaymentDto fromEntity(PaymentEntity paymentEntity){
        return PaymentDto
                .builder()
                .id(paymentEntity.getId())
                .createdDate(paymentEntity.getCreatedDate().getTime())
                .customer(CustomerRawDto.fromEntity(paymentEntity.getCustomers()))
                .paymentStatus(paymentEntity.getStatus())
                .ticket(paymentEntity.getTicket().stream().map(TicketDto::fromEntity).toList())
                .total(paymentEntity.getTotal())
                .paymentType(paymentEntity.getPaymentType())
                .build();
    }
}
