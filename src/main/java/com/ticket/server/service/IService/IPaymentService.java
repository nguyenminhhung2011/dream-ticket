package com.ticket.server.service.IService;

import com.ticket.server.dtos.Payment.*;
import com.ticket.server.dtos.Payment.PaymentManagementPage.PaymentManagementPageDto;
import com.ticket.server.dtos.Payment.PaymentManagementPage.PaymentNoTicketCustomerDto;
import com.ticket.server.entities.PaymentEntity;

import java.util.List;

public interface IPaymentService {
    List<PaymentDto> fetchPaymentData();

    PaymentDto getLatestPaymentByCustomerId(long id);


    List<PaymentNoTicketCustomerDto> searchPaymentItem(String keyword);

    public List<PaymentNoTicketCustomerDto> getPaymentByCustomerId(long id);

    public List<PaymentDto> filterPaymentList(PaymentFilter paymentFilter);

    public PaymentEntity getPaymentById(long id);

    public List<PaymentNoTicketCustomerDto> getPaymentByPage(int page, int perPage);

    public PaymentDtoDetail updatePayment(long id, PaymentDtoDetail status);
    public boolean deletePayment(long id);

    List<PaymentDto> getPaymentByDateRange(long from, long to);

    public PaymentManagementPageDto fetchPaymentManagementPage(int page, int perPage);

    public List<PaymentDto> fetchAllPaymentByCreatedDate(long createdDate);
}
