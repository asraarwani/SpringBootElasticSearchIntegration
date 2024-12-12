package com.organization.www.service;

import com.organization.www.modal.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findByFirstName(String itemName);
}
