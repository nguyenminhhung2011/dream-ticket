package com.ticket.server.controller;

import com.ticket.server.dtos.TicketDtos.AddTicketRequest;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.model.GetListDataRequest;
import com.ticket.server.model.ResponseModel;
import com.ticket.server.model.Ticket;
import com.ticket.server.service.IService.ITicketService;
import com.ticket.server.service.ServiceImpl.TicketServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/ticket")
@RestController
@RequiredArgsConstructor
public class TicketController {

    private final ITicketService ticketService;

    @PostMapping("/add_ticket")
    public ResponseEntity<?> addTicket(@RequestBody AddTicketRequest request){
        try {
            return ResponseEntity.ok(ticketService.addTicket(request));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicket(@PathVariable Long id){
        try {
            return ResponseEntity.ok(ticketService.getTicket(id));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTicket(){
        try {
            return ResponseEntity.ok(ticketService.getAllTicket());
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Server Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id){
        try {
            final boolean result =  ticketService.deleteTicket(id);
            return ResponseEntity.ok(result);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Server error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @RequestBody AddTicketRequest request){
        try {
            return ResponseEntity.ok(ticketService.updateTicket(id,request));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: "+  e.getMessage());
        }
    }

    @GetMapping("/page/page={page}&perPage={perPage}")
    public ResponseEntity<?> getTicketByPage(@PathVariable int page, @PathVariable int perPage){
        try {
            return ResponseEntity.ok(ticketService.getTicketByPage(page,perPage));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }



//    @GetMapping("/get")
//    public ResponseEntity<?> getListTicket(@RequestBody GetListDataRequest request){
//        try {
//            return ResponseEntity.ok(ticketService.getListTicket(request));
//        }catch (Exception e){
//            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
//        }
//    }
}
