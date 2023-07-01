package com.ticket.server.model;
import com.ticket.server.enums.AppUserRole;
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
     private AppUserRole role;
}
