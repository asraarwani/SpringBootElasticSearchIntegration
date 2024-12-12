package com.jpmchase.www.service;

import com.jpmchase.www.modal.Employee;

import java.util.List;

public interface EmployeeOperationsService {

    Integer saveEmployee(Employee employee);

    List<Integer> saveEmployeeInBulk(List<Employee> employeeList);

    Employee findEmployeeById(String id);

    String deleteEmployeeById(String id);
}
