package com.ticket.server.entities;

import com.ticket.server.enums.Gender;
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
    private Long birthday;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "id")
    private List<PaymentEntity> payments;

    @OneToOne(mappedBy = "customer")
    private CreditCardEntity creditCards;
}
