package com.ticket.server.dtos.Payment.PaymentManagementPage;

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
public class PaymentNoCustomerDto {
    private Long id;
    private Long createdDate;
    private Double total;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    private List<TicketDto> ticket;

    public static PaymentNoCustomerDto fromEntity(PaymentEntity paymentEntity){
        return PaymentNoCustomerDto
                .builder()
                .id(paymentEntity.getId())
                .createdDate(paymentEntity.getCreatedDate().getTime())
                .paymentStatus(paymentEntity.getStatus())
                .ticket(paymentEntity.getTicket().stream().map(TicketDto::fromEntity).toList())
                .total(paymentEntity.getTotal())
                .paymentType(paymentEntity.getPaymentType())
                .build();
    }
}
