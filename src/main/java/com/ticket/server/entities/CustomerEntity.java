package com.ticket.server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
