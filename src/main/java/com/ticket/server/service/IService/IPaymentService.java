package com.ticket.server.service.IService;

import com.ticket.server.dtos.Payment.AddPaymentDto;
import com.ticket.server.dtos.Payment.PaymentDto;
import com.ticket.server.dtos.Payment.PaymentFilter;
import com.ticket.server.enums.PaymentStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPaymentService {
    public List<PaymentDto> fetchPaymentData();

    public List<PaymentDto> searchPaymentItem(String keyword);

    public List<PaymentDto> filterPaymentList(PaymentFilter paymentFilter);

    public PaymentDto getPaymentById(long id);

    public List<PaymentDto> getPaymentByPage(int page,int perPage);

    public PaymentDto updatePayment(long id, AddPaymentDto status);
    public boolean deletePayment(long id);

}
