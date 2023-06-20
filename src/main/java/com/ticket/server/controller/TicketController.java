package com.ticket.server.controller;

import com.ticket.server.model.Ticket;
import com.ticket.server.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/ticket")
@RestController
public class TicketController {
    @Autowired
    private final TicketServiceImpl TicketService;

    public TicketController(TicketServiceImpl TicketService) {
        this.TicketService = TicketService;
    }

    @PostMapping("/add")
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket Ticket){
        return TicketService.addTicket(Ticket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id){
        return TicketService.getTicket(id);
    }

    @GetMapping("/")
    public List<Ticket> getAllTicket(){
        return TicketService.getAllTicket();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable Long id){
        return TicketService.deleteTicket(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket Ticket){
        return TicketService.updateTicket(id, Ticket);
    }
}
