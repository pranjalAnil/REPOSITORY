package com.example.CRUD.controller;


import com.example.CRUD.exception.ResourceNotFoundException;
import com.example.CRUD.model.Employee;
import com.example.CRUD.repositrory.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("value/ems/employee")
public class EmployeeController
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

//    Find employee by id
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmpByID(@PathVariable int id){
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee not exist with id: "+id)
                        );
        return ResponseEntity.ok(employee);
    }


    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }


//    Update API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee employeeDetails){
        Employee updateEmployee=employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID not found: "+id));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmail(employeeDetails.getEmail());

        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok( updateEmployee);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Employee> deleteEmployeeByID(@PathVariable int id){
        Employee deleteEmployee=employeeRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Not found ID: "+id)

                );
        employeeRepository.delete(deleteEmployee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
