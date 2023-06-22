package com.ticket.server.dtos.Payment;

import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class PaymentFilter {
    private final boolean asc;
    private final long createDate;
    private final PaymentStatus paymentStatus;
    private final PaymentType paymentType;
    private final int page;
    private final int perPage;
}
