package com.employee.IncomeTaxservice.service;


import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.IncomeTaxservice.DTO.EmployeeDTO;
import com.employee.IncomeTaxservice.model.Employee;
import com.employee.IncomeTaxservice.model.TaxCalculationResponse;
import com.employee.IncomeTaxservice.repo.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumbers(employeeDTO.getPhoneNumbers());
        employee.setDoj(employeeDTO.getDoj());
        employee.setSalary(employeeDTO.getSalary());

        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public TaxCalculationResponse calculateTaxDeductions(String employeeId) {
        Employee employee = getEmployeeById(employeeId);
        LocalDate doj = employee.getDoj();
        LocalDate current = LocalDate.now();

        int monthsWorked = Period.between(doj, current).getMonths();
        double yearlySalary = employee.getSalary() * monthsWorked;

        TaxCalculationResponse taxResponse = new TaxCalculationResponse();
        taxResponse.setEmployeeId(employeeId);
        taxResponse.setFirstName(employee.getFirstName());
        taxResponse.setLastName(employee.getLastName());
        taxResponse.setYearlySalary(yearlySalary);

        double taxAmount = calculateTax(yearlySalary);
        taxResponse.setTaxAmount(taxAmount);

        if (yearlySalary > 2500000) {
            taxResponse.setCessAmount(yearlySalary * 0.02);
        }

        return taxResponse;
    }

    private double calculateTax(double salary) {
        if (salary <= 250000) {
            return 0;
        } else if (salary <= 500000) {
            return (salary - 250000) * 0.05;
        } else if (salary <= 1000000) {
            return 12500 + (salary - 500000) * 0.1;
        } else {
            return 62500 + (salary - 1000000) * 0.2;
        }
    }
}

