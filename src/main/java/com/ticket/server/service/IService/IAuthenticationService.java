package com.ticket.server.service.IService;

import com.ticket.server.model.AuthenticationRequest;
import com.ticket.server.model.AuthenticationResponse;
import com.ticket.server.model.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {
    ResponseEntity<?> register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    ResponseEntity<?> confirmEmail(String confirmationToken);

    ResponseEntity<?> refreshToken(String refreshToken);

    ResponseEntity<?> changePassword(String username,String newPassword);


}

