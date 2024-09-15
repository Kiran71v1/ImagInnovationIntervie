package com.employee.IncomeTaxservice.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.IncomeTaxservice.DTO.EmployeeDTO;
import com.employee.IncomeTaxservice.model.Employee;
import com.employee.IncomeTaxservice.model.TaxCalculationResponse;
import com.employee.IncomeTaxservice.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        Employee createdEmployee = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/tax-deductions")
    public ResponseEntity<TaxCalculationResponse> getTaxDeductions(@PathVariable String employeeId) {
        TaxCalculationResponse response = employeeService.calculateTaxDeductions(employeeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
