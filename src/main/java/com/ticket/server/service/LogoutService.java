package com.ticket.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.server.model.AuthenticationResponse;
import com.ticket.server.repository.AppTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class LogoutService implements LogoutHandler {

    private final AppTokenRepository appTokenRepository;
    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication
    ) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null){
            return;
        }

        final String jwt = authHeader.substring(7);
        var storedJwt = appTokenRepository.findByToken(jwt).orElse(null);

        if (storedJwt != null){
            storedJwt.setExpired(true);
            storedJwt.setRevoke(true);
            appTokenRepository.save(storedJwt);
        }


    }
}
