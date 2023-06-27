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
    private CustomerRawDto customer;

    public static PaymentNoTicketCustomerDto fromEntity(PaymentEntity paymentEntity){
        return PaymentNoTicketCustomerDto
                .builder()
                .id(paymentEntity.getId())
                .createdDate(paymentEntity.getCreatedDate().getTime())
                .paymentStatus(paymentEntity.getStatus())
                .total(paymentEntity.getTotal())
                .paymentType(paymentEntity.getPaymentType())
                .customer(CustomerRawDto.fromEntity(paymentEntity.getCustomers()))
                .build();
    }

    public static PaymentNoTicketCustomerDto fromPaymentDto(PaymentDto paymentDto){
        return PaymentNoTicketCustomerDto
                .builder()
                .id(paymentDto.getId())
                .createdDate(paymentDto.getCreatedDate())
                .paymentStatus(paymentDto.getPaymentStatus())
                .total(paymentDto.getTotal())
                .paymentType(paymentDto.getPaymentType())
                .customer(paymentDto.getCustomer())
                .build();
    }

}
