package com.ticket.server.dtos.Payment.PaymentManagementPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public
class TicketTierData {
    private final long economy;
    private final long premiumEconomy;
    private final long business;
    private final long first;
}
