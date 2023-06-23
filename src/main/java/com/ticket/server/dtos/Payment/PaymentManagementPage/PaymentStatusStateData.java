package com.ticket.server.dtos.Payment.PaymentManagementPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public
class PaymentStatusStateData {
    private final long create;
    private final long declined;
    private final long pending;
    private final long succeeded;
}
