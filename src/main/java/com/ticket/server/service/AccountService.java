package com.ticket.server.service;

import com.ticket.server.model.Account;
import com.ticket.server.model.UserInformation;
import com.ticket.server.repository.AccountRepository;
import com.ticket.server.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final UserInformationRepository userInformationRepository;

    public AccountService(AccountRepository accountRepository, UserInformationRepository userInformationRepository) {
        this.accountRepository = accountRepository;
        this.userInformationRepository = userInformationRepository;
    }

    public ResponseEntity<Account> addAccount(Account account){
        Account createdAccount = accountRepository.save(account);
        return ResponseEntity.created(URI.create("/account/" + createdAccount.getId())).body(createdAccount);
    }

    public ResponseEntity<Void> deleteAccount(Long id){
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Account account = optionalAccount.get();
        accountRepository.delete(account);
        userInformationRepository.delete(account.getUserInformation());

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Account> getAccount(Long id){
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.map(account -> ResponseEntity.ok().body(account))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<Account> getAllAccount(){
        return accountRepository.findAll();
    }

    public ResponseEntity<Account> updateAccount(Long id, Account account){
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(account.getUserInformation() == null){
            UserInformation userInformation = new UserInformation();
            account.setUserInformation(userInformation);
        }

        account.setId(id);
        account.getUserInformation().setId(id);
        Account updatedAccount = accountRepository.save(account);
        return ResponseEntity.ok().body(updatedAccount);
    }
}
