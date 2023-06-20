package com.ticket.server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String identifyNum;
    private int birthday;
    private String phone;
    private String email;

    @OneToMany
    private List<PaymentEntity> payments;

    @OneToMany
    private List<CreditCardEntity> creditCards;
}
