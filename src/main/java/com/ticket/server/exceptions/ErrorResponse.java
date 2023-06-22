package com.ticket.server.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private final String message;
    private final Timestamp date;
}
