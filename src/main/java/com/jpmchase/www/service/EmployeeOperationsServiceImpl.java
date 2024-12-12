package com.jpmchase.www.service;

import com.jpmchase.www.modal.Employee;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeOperationsServiceImpl implements EmployeeOperationsService {

    private final ElasticsearchOperations elasticsearchOperations;

    public EmployeeOperationsServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public Integer saveEmployee(Employee employee) {
        Employee savedEmployee = elasticsearchOperations.save(employee);
        System.out.println("New employee indexed, id " + employee.getId());
        return savedEmployee.getId();
    }

    @Override
    public List<Integer> saveEmployeeInBulk(List<Employee> employeeList) {
        List<Integer> savedEmployeeIdList = new ArrayList<>();
        elasticsearchOperations.save(employeeList).forEach(i -> savedEmployeeIdList.add(i.getId()));
        System.out.println("New employees indexed, ids " + Arrays.toString(savedEmployeeIdList.toArray()));
        return savedEmployeeIdList;
    }

    @Override
    public Employee findEmployeeById(String id) {
        return elasticsearchOperations.get(id, Employee.class);
    }

    @Override
    public String deleteEmployeeById(String id) {
        return elasticsearchOperations.delete(id, Employee.class);
    }
}
