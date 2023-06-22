package com.ticket.server.controller;

import com.ticket.server.dtos.Payment.AddPaymentDto;
import com.ticket.server.dtos.Payment.PaymentDto;
import com.ticket.server.dtos.Payment.PaymentFilter;
import com.ticket.server.service.IService.IPaymentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.jpa.domain.AbstractAuditable_;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@AllArgsConstructor

public class PaymentController{
    private final IPaymentService paymentService;

    @ExceptionHandler(value = {RuntimeException.class})
    ResponseEntity<?> handleException(Exception ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPaymentService(){
        return ResponseEntity.ok(paymentService.fetchPaymentData());
    }

    @GetMapping("/")
    public ResponseEntity<?> getPaymentByPage(@RequestParam int page,@RequestParam int perPage){
        return ResponseEntity.ok(paymentService.getPaymentByPage(page,perPage));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPayment(@RequestParam String keyword){
        return ResponseEntity.ok(paymentService.searchPaymentItem(keyword));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterPayment(@RequestBody PaymentFilter paymentFilter){
        return ResponseEntity.ok(paymentService.filterPaymentList(paymentFilter));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPaymentById(@RequestParam long id){
        final PaymentDto paymentDto = paymentService.getPaymentById(id);

        final ResponseEntity response = ResponseEntity.ok(paymentDto);

        System.out.println(response.getBody());

        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePayment(@RequestParam long id,@RequestBody AddPaymentDto paymentDto){
        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("success",paymentService.updatePayment(id,paymentDto));

        return ResponseEntity.ok(responseMap);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePayment(@RequestParam long id){
        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("success", paymentService.deletePayment(id));

        return ResponseEntity.ok(responseMap);
    }
}
