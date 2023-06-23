package com.ticket.server.dtos.Payment.PaymentManagementPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public
class Revenue {
    private final double amount;
    private final double cardPercent;
    private final double cashPercent;
    private final double otherPercent;
}
