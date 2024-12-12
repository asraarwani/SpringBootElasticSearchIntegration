package com.organization.www.service;

import com.organization.www.modal.Employee;
import com.organization.www.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findByFirstName(String itemName) {
        return employeeRepository.findByFirstName(itemName);
    }
}
