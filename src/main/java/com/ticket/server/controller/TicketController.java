package com.ticket.server.controller;

import com.ticket.server.dtos.TicketDtos.AddTicketRequest;
import com.ticket.server.dtos.TicketDtos.TicketRequest;
import com.ticket.server.service.IService.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1/ticket")
@RestController
@RequiredArgsConstructor
public class TicketController {

    private final ITicketService ticketService;

    @PostMapping("/book_ticket")
    public ResponseEntity<?> addTicket(@RequestBody AddTicketRequest request){
        return ResponseEntity.ok(ticketService.addTicket(request));
    }

    @GetMapping("/{id}")
     public ResponseEntity<?> getTicket(@PathVariable Long id){
       try {
            return ResponseEntity.ok(ticketService.getTicket(id ));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/flightId={id}")
    public ResponseEntity<?> getByFlightId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(ticketService.getByFlight(id));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error" + e.getMessage());
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

    @DeleteMapping(value = "/delete",params = {"id"})
    public ResponseEntity<?> deleteTicket(@RequestParam Long id){
        try {
            ticketService.deleteTicket(id);
            final Map<String,Object> result = new HashMap<>();
            result.put("isSuccess",true);
            return ResponseEntity.ok(result);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Server error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @RequestBody TicketRequest request){
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
