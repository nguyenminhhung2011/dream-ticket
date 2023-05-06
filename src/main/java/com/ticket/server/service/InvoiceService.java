package com.ticket.server.service;

import com.ticket.server.model.Invoice;
import com.ticket.server.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public ResponseEntity<Invoice> addInvoice(Invoice invoice){
        Invoice createdInvoice = invoiceRepository.save(invoice);
        return ResponseEntity.created(URI.create("/invoice/" + createdInvoice.getId())).body(createdInvoice);
    }

    public ResponseEntity<Invoice> getInvoice(Long id){
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        return optionalInvoice.map(Invoice -> ResponseEntity.ok().body(Invoice))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<Invoice> getAllInvoice(){
        return invoiceRepository.findAll();
    }

    public ResponseEntity<Invoice> deleteInvoice(Long id){
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if(optionalInvoice.isPresent()){
            invoiceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Invoice> updateInvoice(Long id, Invoice invoice){
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if(optionalInvoice.isPresent()){
            Invoice updatedInvoice = optionalInvoice.get();
            updatedInvoice.setCreatedDate(invoice.getCreatedDate());
            updatedInvoice.setTotalPrice(invoice.getTotalPrice());

            invoiceRepository.save(updatedInvoice);
            return ResponseEntity.ok().body(updatedInvoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
