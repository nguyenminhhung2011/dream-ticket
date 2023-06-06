package com.ticket.server.repository;

import com.ticket.server.model.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountName(String accountName);
    List<Account> findByAccountNameContainingIgnoreCase(String accountName);
    List<Account> findAll(Specification<Account> spec, Sort sort);
}
