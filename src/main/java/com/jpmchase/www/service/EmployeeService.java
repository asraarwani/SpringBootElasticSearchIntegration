package com.jpmchase.www.service;

import com.jpmchase.www.modal.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findByFirstName(String itemName);
}
