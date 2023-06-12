package com.ticket.server.repository;

import com.ticket.server.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByAccount(String account);
    List<Employee> findByFullNameOrAccountIgnoreCase(String fullName, String account);
    List<Employee> findAll(Specification<Employee> spec, Sort sort);
}
