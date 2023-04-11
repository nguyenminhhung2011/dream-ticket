package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    private Long flight;
    private Integer ticketClass;
    private Integer price;
}
