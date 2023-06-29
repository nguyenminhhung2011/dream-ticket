package com.ticket.server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Data

public class TotalByDateRange {
    Timestamp date;
    long total;

    public LocalDate getDate() {
        return date.toLocalDateTime().toLocalDate();
    }
}
