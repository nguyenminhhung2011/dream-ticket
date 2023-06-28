package com.ticket.server.controller;

import com.ticket.server.dtos.CreditCard.CreditCardDto;
import com.ticket.server.dtos.CustomerDtos.CustomerCreditCardDto;
import com.ticket.server.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credit_card")
@AllArgsConstructor
public class CreditCardController {
    private final CreditCardRepository creditCardRepository;

    @GetMapping("/getByCustomer/{id}")
    public ResponseEntity<?> getCreditCardByCustomerId(@PathVariable long id){
        final CreditCardDto result = CreditCardDto.fromEntity(creditCardRepository
                .findAllByCustomerId(id));

        return ResponseEntity.ok(result);

    }

}
