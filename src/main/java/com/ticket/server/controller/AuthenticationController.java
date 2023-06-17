package com.ticket.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.server.model.AuthenticationRequest;
import com.ticket.server.model.AuthenticationResponse;
import com.ticket.server.model.RegisterRequest;
import com.ticket.server.service.IService.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService _authAuthenticationService;
    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        return _authAuthenticationService.register(request);
    }

    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
            return ResponseEntity.ok(_authAuthenticationService.authenticate(request));
    }

    @RequestMapping(value = "/confirm_email",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken){
        return _authAuthenticationService.confirmEmail(confirmationToken);
    }

    @GetMapping("/refresh_token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.contains("Bearer")){
            ResponseEntity.badRequest().body("Missing header authorization");
            try {
                new ObjectMapper().writeValue(response.getOutputStream(), "Missing header authorization");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            assert authHeader != null;
            final String refreshToken = authHeader.substring(7);
             _authAuthenticationService.refreshToken(response,refreshToken);
             return;
        }catch (Exception e){
            System.out.println(e);
        }

        try {
            new ObjectMapper().writeValue(response.getOutputStream(), "Can not found refresh token in header authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
