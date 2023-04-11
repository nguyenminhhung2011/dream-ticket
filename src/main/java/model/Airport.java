package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long airportId;

    @Column(length = 50)
    private String airportName;

    @Column(length = 50)
    private String location;
}