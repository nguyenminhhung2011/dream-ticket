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
    private long id;
    private String creditNum;
    private long expiredDate;
    private String cvc;
    private String nameCard;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

}
