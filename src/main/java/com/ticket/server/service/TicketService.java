package com.ticket.server.service;

import com.ticket.server.model.Ticket;
import com.ticket.server.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private final TicketRepository TicketRepository;

    public TicketService(TicketRepository TicketRepository) {
        this.TicketRepository = TicketRepository;
    }

    public ResponseEntity<Ticket> addTicket(Ticket Ticket){
        Ticket createdTicket = TicketRepository.save(Ticket);
        return ResponseEntity.created(URI.create("/ticket/" + createdTicket.getId())).body(createdTicket);
    }

    public ResponseEntity<Ticket> getTicket(Long id){
        Optional<Ticket> optionalTicket = TicketRepository.findById(id);
        return optionalTicket.map(Ticket -> ResponseEntity.ok().body(Ticket))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<Ticket> getAllTicket(){
        return TicketRepository.findAll();
    }

    public ResponseEntity<Ticket> deleteTicket(Long id){
        Optional<Ticket> optionalTicket = TicketRepository.findById(id);
        if(optionalTicket.isPresent()){
            TicketRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Ticket> updateTicket(Long id, Ticket Ticket){
        Optional<Ticket> optionalTicket = TicketRepository.findById(id);
        if(optionalTicket.isPresent()){
            Ticket updatedTicket = optionalTicket.get();
            updatedTicket.setFullName(Ticket.getFullName());
            updatedTicket.setIdentityCard(Ticket.getIdentityCard());
            updatedTicket.setPhone(Ticket.getPhone());
            updatedTicket.setFlight(Ticket.getFlight());

            TicketRepository.save(updatedTicket);
            return ResponseEntity.ok().body(updatedTicket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
