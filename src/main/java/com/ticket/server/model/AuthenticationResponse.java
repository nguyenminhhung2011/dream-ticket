package com.ticket.server.model;
import lombok.*;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
     private final String token;
     private Boolean isSuccess;
     private String message;
}
