package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long airplaneId;

    private Integer seatNumber;
}
