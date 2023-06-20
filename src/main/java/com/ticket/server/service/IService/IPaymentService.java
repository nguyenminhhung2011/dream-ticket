package com.ticket.server.service.IService;

import org.springframework.http.ResponseEntity;

public interface IPaymentService {
    public ResponseEntity<?> fetchPaymentData();

    public ResponseEntity<?> searchPaymentItem();

    public ResponseEntity<?> filterPaymentList();

    public ResponseEntity<?> getPaymentDetail();

    public ResponseEntity<?> deletePayment();

    public ResponseEntity<?> updatePayment();
}
