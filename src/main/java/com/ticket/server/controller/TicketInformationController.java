package com.ticket.server.controller;

import com.ticket.server.dtos.TicInformationDto.AddTicInformationRequest;
import com.ticket.server.service.IService.ITicketInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public  ResponseEntity<?> addTicketInformation(@RequestBody AddTicInformationRequest addTicInformationRequest){
        try{
            return ResponseEntity.ok(ticketInformationService.addTicketInformation(addTicInformationRequest));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
