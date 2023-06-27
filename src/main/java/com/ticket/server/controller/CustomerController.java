package com.ticket.server.controller;

import com.ticket.server.dtos.CustomerDtos.AddCustomerRequest;
import com.ticket.server.dtos.CustomerDtos.CustomerRawDto;
import com.ticket.server.repository.CustomerRepository;
import com.ticket.server.service.IService.ICustomerService;
import com.ticket.server.service.ServiceImpl.CustomerService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<?> getALlCustomer(){
        try {
            return ResponseEntity.ok(customerService.getAllCustomer());
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchCustomer(
        @PathVariable("keyword") String keyword){
        try {
            return ResponseEntity.ok(customerService.searchCustomer(keyword));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable long id){
        try {
            return ResponseEntity.ok(customerService.getCustomerById(id));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long id){
        try {
            Map<String,Object> response = new HashMap<>();

            response.put("isSuccess ",customerService.deleteCustomer(id));

            return ResponseEntity.ok(response);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewCustomer(@RequestBody AddCustomerRequest request){
        try {
            return ResponseEntity.ok(customerService.addNewCustomer(request));
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerRawDto customerRawDto){
        try {
            return ResponseEntity.ok(customerService.updateNewCustomer(customerRawDto));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
