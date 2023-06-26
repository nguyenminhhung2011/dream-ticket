package com.ticket.server.dtos.Payment.PaymentManagementPage;

import com.ticket.server.dtos.Payment.AddPaymentDto;
import com.ticket.server.dtos.Payment.PaymentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PaymentManagementPageDto {
    private List<PaymentNoTicketCustomerDto> payments;
    private TotalStatisticalData totalData;
    private Revenue revenue;
    private PaymentStatusStateData statusData;
    private TicketTierData ticketTierData;
    private long totalPayment;
}

