package com.ticket.server.dtos.TicketDtos;

import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.Date;

@Data
@AllArgsConstructor
public class TicketFilterRequest {
    private Date fromDate;
    private Date toDate;
    private String keyword;
    private boolean isAscending;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
}
