package com.ticket.server.dtos.OverViewDto;

import com.ticket.server.dtos.Payment.PaymentManagementPage.TicketTierData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class OverviewModelDataDto {
    private  long totalAirport;
    private  long totalCustomer;
    private  long totalFlight;
    private  long totalPayment;
    private List<Long> flightByWeek;
    private List<Long> customerByWeek;
    private List<Map<String, Long>> paymentByWeek;
    private TicketTierData ticketTierData;
    }
