package com.ticket.server.model;
import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
     private final String accessToken;
     private final String refreshToken;
     private final Long expiredTime;
     private Boolean isSuccess;
     private String message;
}
