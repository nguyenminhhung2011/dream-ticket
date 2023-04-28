package com.ticket.server.service;

import com.ticket.server.model.Employee;
import com.ticket.server.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Employee> addEmployee(Employee employee){
        Employee createdEmployee = employeeRepository.save(employee);
        return ResponseEntity.created(URI.create("/employee/" + createdEmployee.getId())).body(createdEmployee);
    }

    public ResponseEntity<Employee> getEmployee(Long id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.map(employee -> ResponseEntity.ok().body(employee))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public ResponseEntity<Employee> deleteEmployee(Long id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Employee> updateEmployee(Long id, Employee employee){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Employee updatedEmployee = optionalEmployee.get();
            updatedEmployee.setAccount(employee.getAccount());
            updatedEmployee.setPassword(employee.getPassword());
            updatedEmployee.setFullName(employee.getFullName());
            updatedEmployee.setIdentifyCard(employee.getIdentifyCard());
            updatedEmployee.setAddress(employee.getAddress());
            updatedEmployee.setPhone(employee.getPhone());

            employeeRepository.save(updatedEmployee);
            return ResponseEntity.ok().body(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
