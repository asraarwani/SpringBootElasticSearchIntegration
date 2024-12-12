package com.organization.www.repository;

import com.organization.www.modal.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee, Integer> {

    List<Employee> findByFirstName(String firstName);
}
