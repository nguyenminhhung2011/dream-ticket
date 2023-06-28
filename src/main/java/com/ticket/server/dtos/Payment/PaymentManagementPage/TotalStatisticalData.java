package com.ticket.server.dtos.Payment.PaymentManagementPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public
class TotalStatisticalData {
    private final long totalPaymentToday;
    private final long totalCustomerToday;
    private final long totalFlightToday;
    private final long totalPassengerToday;
}
