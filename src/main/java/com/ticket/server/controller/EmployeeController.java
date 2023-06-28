package com.ticket.server.controller;

import com.ticket.server.model.Employee;
import com.ticket.server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @GetMapping("/")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestParam(name = "fullName") String fullName,
            @RequestParam(name = "account") String account){
        return employeeService.searchEmployees(fullName, account);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Employee>> getEmployeesSortedBy(
            @RequestParam(name = "sortBy") String sortBy,
            @RequestParam(name = "ascending", defaultValue = "true") boolean ascending) {
        return employeeService.getEmployeesSortedBy(sortBy, ascending);
    }
}
