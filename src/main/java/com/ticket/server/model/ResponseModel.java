package com.ticket.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class ResponseModel<T> {
    private String message;
    private boolean success;
    private T data;
}
