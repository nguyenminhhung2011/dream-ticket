package com.ticket.server.service.IService;

import com.ticket.server.dtos.Payment.*;
import com.ticket.server.dtos.Payment.PaymentManagementPage.PaymentManagementPageDto;
import com.ticket.server.dtos.Payment.PaymentManagementPage.PaymentNoCustomerDto;

import java.util.List;

public interface IPaymentService {
    List<PaymentDto> fetchPaymentData();

    PaymentDto getLatestPaymentByCustomerId(long id);

    List<PaymentDto> searchPaymentItem(String keyword);

    public List<PaymentNoCustomerDto> getPaymentByCustomerId(long id);

    public List<PaymentDto> filterPaymentList(PaymentFilter paymentFilter);

    public PaymentDto getPaymentById(long id);

    public List<PaymentDto> getPaymentByPage(int page,int perPage);

    public PaymentDto updatePayment(long id, AddPaymentDto status);
    public boolean deletePayment(long id);

    public PaymentManagementPageDto fetchPaymentManagementPage();

    public List<PaymentDto> fetchAllPaymentByCreatedDate(long createdDate);
}
