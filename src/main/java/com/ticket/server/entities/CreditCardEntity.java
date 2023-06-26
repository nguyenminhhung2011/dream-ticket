package com.ticket.server.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String creditNum;
    private Long expiredDate;
    private String cvc;
    private String nameCard;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

}
