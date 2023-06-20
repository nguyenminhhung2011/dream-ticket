package com.ticket.server.service.ServiceImpl;

import com.ticket.server.service.IService.IPaymentService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public class PaymentServiceImpl implements IPaymentService {

    @Override
    public ResponseEntity<?> fetchPaymentData() {
        return  null;
    }

    @Override
    public ResponseEntity<?> searchPaymentItem() {
        return null;
    }

    @Override
    public ResponseEntity<?> filterPaymentList() {
        return null;
    }

    @Override
    public ResponseEntity<?> getPaymentDetail() {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePayment() {
        return null;
    }

    @Override
    public ResponseEntity<?> updatePayment() {
        return null;
    }
}
