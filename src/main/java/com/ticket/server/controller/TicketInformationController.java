package com.ticket.server.controller;

import com.ticket.server.service.IService.ITicketInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/ticketInformation")
@RestController
@RequiredArgsConstructor
public class TicketInformationController {
    private  final ITicketInformationService ticketInformationService;

    @GetMapping("/flight={id}")
    public ResponseEntity<?> getTicketInformationByFlight(@PathVariable Long id){
        try {
            return ResponseEntity.ok(ticketInformationService.getTicketInformationByFlight(id));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
