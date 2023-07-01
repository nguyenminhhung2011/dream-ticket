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
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){
        return _authAuthenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
            return ResponseEntity.ok(_authAuthenticationService.authenticate(request));
    }

    @RequestMapping(value = "/confirm_email",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken){
        return _authAuthenticationService.confirmEmail(confirmationToken);
    }

    @GetMapping("/refresh_token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response){

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.contains("Bearer")){
            return ResponseEntity.badRequest().body("Missing header authorization");
        }

        final String refreshToken = authHeader.substring(7);
        return _authAuthenticationService.refreshToken(refreshToken);
    }


    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(
        @RequestParam String username,
        @RequestParam String newPassword
    ){
        return _authAuthenticationService.changePassword(username,newPassword);
    }

}
