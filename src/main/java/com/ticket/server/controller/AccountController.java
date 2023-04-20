package com.ticket.server.controller;

import com.ticket.server.model.Account;
import com.ticket.server.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @GetMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account){
        return accountService.loginAccount(account);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        return accountService.deleteAccount(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id){
        return accountService.getAccount(id);
    }

    @GetMapping("/")
    public List<Account> getAllAccount(){
        return accountService.getAllAccount();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account){
        return accountService.updateAccount(id, account);
    }
}
