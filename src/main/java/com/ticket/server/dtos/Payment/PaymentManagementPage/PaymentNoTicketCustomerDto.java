package com.ticket.server.dtos.Payment.PaymentManagementPage;

import com.ticket.server.dtos.CustomerDtos.CustomerRawDto;
import com.ticket.server.dtos.Payment.PaymentDto;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.entities.PaymentEntity;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class PaymentNoTicketCustomerDto {
    private Long id;
    private Long createdDate;
    private Double total;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;

    public static PaymentNoTicketCustomerDto fromEntity(PaymentEntity paymentEntity){
        return PaymentNoTicketCustomerDto
                .builder()
                .id(paymentEntity.getId())
                .createdDate(paymentEntity.getCreatedDate().getTime())
                .paymentStatus(paymentEntity.getStatus())
                .total(paymentEntity.getTotal())
                .paymentType(paymentEntity.getPaymentType())
                .build();
    }

}
