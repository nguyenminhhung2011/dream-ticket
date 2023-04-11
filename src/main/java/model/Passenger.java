package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10)
    private String firstName;

    @Column(length = 10)
    private String lastName;

    @Column(length = 20)
    private String identityCard;

    @Column(length = 20)
    private String phone;
}
