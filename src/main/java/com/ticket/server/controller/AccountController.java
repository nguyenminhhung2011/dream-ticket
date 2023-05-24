package com.ticket.server.controller;

import com.ticket.server.data.UserCredentials;
import com.ticket.server.model.Account;
import com.ticket.server.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody UserCredentials credentials){
        return accountService.login(credentials);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        return accountService.deleteAccount(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id){
        return accountService.getAccount(id);
    }

    @GetMapping("/username/{accountName}")
    public ResponseEntity<Account> getAccountByAccountName(@PathVariable String accountName){
        return accountService.getAccountByAccountName(accountName);
    }

    @GetMapping("/")
    public ResponseEntity<List<Account>> getAllAccount(){
        List<Account> accounts = accountService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account){
        return accountService.updateAccount(id, account);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Account>> searchAccounts(@RequestParam("keyword") String keyword){
        List<Account> accounts = accountService.searchAccounts(keyword);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Account>> sortAccounts(@RequestParam(value = "sortBy", defaultValue = "id") String sortBy){
        List<Account> accounts = accountService.sortAccounts(sortBy);
        return ResponseEntity.ok(accounts);
    }
}
