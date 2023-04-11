package model;

import lombok.Data;

import java.util.Date;

@Data
public class Stopover {
    private Long stopArea;
    private Date arrivalTime;
    private Date departureTime;
}

