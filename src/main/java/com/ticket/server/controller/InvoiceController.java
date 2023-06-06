package com.ticket.server.controller;

import com.ticket.server.model.Invoice;
import com.ticket.server.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/invoice")
@RestController
public class InvoiceController {
    @Autowired
    private final InvoiceService InvoiceService;

    public InvoiceController(InvoiceService InvoiceService) {
        this.InvoiceService = InvoiceService;
    }

    @PostMapping("/add")
    public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice Invoice){
        return InvoiceService.addInvoice(Invoice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long id){
        return InvoiceService.getInvoice(id);
    }

    @GetMapping("/")
    public List<Invoice> getAllInvoice(){
        return InvoiceService.getAllInvoice();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable Long id){
        return InvoiceService.deleteInvoice(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice Invoice){
        return InvoiceService.updateInvoice(id, Invoice);
    }
}
